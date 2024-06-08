package com.modul.buahhati.view.fragment.fragment_home

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.modul.buahhati.R
import com.modul.buahhati.data.remote.LoginPreference
import com.modul.buahhati.data.remote.dataStore
import com.modul.buahhati.di.Injection
import com.modul.buahhati.view.ViewModelFactory
import com.modul.buahhati.view.welcome.WelcomeActivity

class ProfileFragment : Fragment() {

    private lateinit var viewModel: ProfileViewModel
    private lateinit var loginPreference: LoginPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        loginPreference = LoginPreference.getInstance(requireContext().dataStore)

        val factory =
            ViewModelFactory(Injection.provideRepository(requireContext()), loginPreference)
        viewModel = ViewModelProvider(this, factory).get(ProfileViewModel::class.java)

        return inflater.inflate(R.layout.fragment_profile, container, false)
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
                        intent.flags =
                            Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
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
}