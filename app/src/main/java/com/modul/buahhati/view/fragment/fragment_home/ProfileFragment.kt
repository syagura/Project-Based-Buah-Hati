package com.modul.buahhati.view.fragment.fragment_home

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.modul.buahhati.R
import com.modul.buahhati.adapter.ProfileAdapter
import com.modul.buahhati.data.remote.LoginPreference
import com.modul.buahhati.data.remote.dataStore
import com.modul.buahhati.di.Injection
import com.modul.buahhati.view.ViewModelFactory
import com.modul.buahhati.view.welcome.WelcomeActivity
import com.modul.buahhati.data.remote.Result
import com.modul.buahhati.view.regis_anak.RegistrasiAnakActivity
import com.modul.buahhati.view.regis_anak.SharedViewModel

class ProfileFragment : Fragment() {

    private lateinit var viewModel: ProfileViewModel
    private lateinit var loginPreference: LoginPreference
    private lateinit var profileAdapter: ProfileAdapter
    private lateinit var sharedViewModel: SharedViewModel

    private lateinit var progressBar: ProgressBar
    private lateinit var tvNameUser: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        loginPreference = LoginPreference.getInstance(requireContext().dataStore)
        val factory = ViewModelFactory(Injection.provideRepository(requireContext()), loginPreference)
        viewModel = ViewModelProvider(this, factory).get(ProfileViewModel::class.java)
        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)

        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        setupRecyclerView(view)
        setupObserver()
        buttonAddAnak(view)

        tvNameUser = view.findViewById(R.id.tv_name_user)
        progressBar = view.findViewById(R.id.progressBar)

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.top_nav_logout, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_logout -> {
                AlertDialog.Builder(activity).apply {
                    setTitle(getString(R.string.logout))
                    setMessage(getString(R.string.are_your_sure))
                    setPositiveButton(getString(R.string.continue_message)) { _, _ ->
                        viewModel.logout()
                        val intent = Intent(activity, WelcomeActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(intent)
                    }
                    create()
                    show()
                }
                true
            }
            else -> false
        }
    }

    private fun setupRecyclerView(view: View) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.rv_profile)
        profileAdapter = ProfileAdapter()
        recyclerView.adapter = profileAdapter
        recyclerView.layoutManager = LinearLayoutManager(context)  // Tambahkan LayoutManager di sini
    }

    private fun setupObserver() {
        viewModel.getUserId().observe(viewLifecycleOwner) { userId ->
            userId?.let {
                viewModel.getChildren(it).observe(viewLifecycleOwner) { result ->
                    when (result) {
                        is Result.Loading -> {
                            progressBar.visibility = View.VISIBLE
                            Log.d("ProfileFragment", "Loading children data")
                        }
                        is Result.Success -> {
                            progressBar.visibility = View.GONE
                            Log.d("ProfileFragment", "Children data loaded successfully: ${result.data}")
                            profileAdapter.submitList(result.data)
                        }
                        is Result.Error -> {
                            progressBar.visibility = View.GONE
                            Log.e("ProfileFragment", "Error loading children data: ${result.error}")
                        }
                    }
                }
            }
        }
        viewModel.getUserName().observe(viewLifecycleOwner) { userName ->
            userName?.let {
                tvNameUser.text = it
            }
        }

        sharedViewModel.updateProfile.observe(viewLifecycleOwner) { shouldUpdate ->
            if (shouldUpdate) {
                viewModel.getUserId().observe(viewLifecycleOwner) { userId ->
                    userId?.let {
                        viewModel.getChildren(it).observe(viewLifecycleOwner) { result ->
                            when (result) {
                                is Result.Loading -> {
                                    progressBar.visibility = View.VISIBLE
                                    Log.d("ProfileFragment", "Loading children data after update")
                                }
                                is Result.Success -> {
                                    progressBar.visibility = View.GONE
                                    Log.d("ProfileFragment", "Children data loaded successfully after update: ${result.data}")
                                    profileAdapter.submitList(result.data)
                                }
                                is Result.Error -> {
                                    progressBar.visibility = View.GONE
                                    Log.e("ProfileFragment", "Error loading children data after update: ${result.error}")
                                }
                            }
                        }
                    }
                }
                sharedViewModel.setUpdateProfile(false) // Reset signal
            }
        }
    }

    private fun buttonAddAnak(view: View) {
        view.findViewById<Button>(R.id.bt_add_anak).setOnClickListener {
            val intent = Intent(activity, RegistrasiAnakActivity::class.java)
            startActivity(intent)
        }
    }
}
