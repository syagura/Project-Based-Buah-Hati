package com.modul.buahhati.view.sign_up

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.modul.buahhati.R
import com.modul.buahhati.data.remote.LoginPreference
import com.modul.buahhati.data.remote.dataStore
import com.modul.buahhati.databinding.ActivitySignUpBinding
import com.modul.buahhati.view.ViewModelFactory
import com.modul.buahhati.data.remote.Result
import com.modul.buahhati.view.login.LoginActivity
import com.modul.buahhati.view.welcome.WelcomeActivity

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.progressBar.visibility = View.GONE

        setupView()
        signUp()
        playAnimation()
        syncNameAndUsername()
    }

    private fun setupView() {
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAGS_CHANGED,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        supportActionBar?.hide()
    }

    private fun signUp() {
        val factory: ViewModelFactory =
            ViewModelFactory.getInstance(
                this,
                LoginPreference.getInstance(dataStore)
            )

        val viewModel: SignUpViewModel =
            ViewModelProvider(this, factory)[SignUpViewModel::class.java]

        binding.btnRegister.setOnClickListener {
            val name = binding.edRegisterName.text.toString()
            val email = binding.edRegisterEmail.text.toString()
            val password = binding.edRegisterPassword.text.toString()
            val username = binding.edRegisterUsername.text.toString()
            viewModel.signup(name, username, email, password).observe(this) {
                if (it != null) {
                    when (it) {
                        is Result.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                        }

                        is Result.Success -> {
                            binding.progressBar.visibility = View.GONE
                            val respon = it.data
                            AlertDialog.Builder(this).apply {
                                setTitle(R.string.success)
                                setMessage(respon.message)
                                setPositiveButton(getString(R.string.continue_message)) { _, _ ->
                                    startActivity(
                                        Intent(
                                            this@SignUpActivity,
                                            LoginActivity::class.java
                                        )
                                    )
                                }
                                create()
                                show()
                            }.apply {
                                setOnCancelListener {
                                    startActivity(
                                        Intent(
                                            this@SignUpActivity,
                                            WelcomeActivity::class.java
                                        )
                                    )
                                }
                                show()
                            }
                        }

                        is Result.Error -> {
                            binding.progressBar.visibility = View.GONE
                            AlertDialog.Builder(this).apply {
                                setTitle(getString(R.string.error_message))
                                setMessage(it.error)
                                setPositiveButton(getString(R.string.continue_message)) { _, _ -> }
                                create()
                                show()
                            }
                        }
                    }
                }
            }
        }
    }

    private fun syncNameAndUsername() {
        binding.edRegisterName.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                binding.edRegisterUsername.setText(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    private fun playAnimation() {

        val title = ObjectAnimator.ofFloat(binding.tvTitle, View.ALPHA, 1f).setDuration(100)
        val nameTextView =
            ObjectAnimator.ofFloat(binding.tvName, View.ALPHA, 1f).setDuration(100)
        val nameEditTextLayout =
            ObjectAnimator.ofFloat(binding.nameEditTextLayout, View.ALPHA, 1f).setDuration(100)
        val usernamenameTextView =
            ObjectAnimator.ofFloat(binding.tvUsername, View.ALPHA, 1f).setDuration(100)
        val usernameEditTextLayout =
            ObjectAnimator.ofFloat(binding.usernameEditTextLayout, View.ALPHA, 1f).setDuration(100)
        val emailTextView =
            ObjectAnimator.ofFloat(binding.tvEmail, View.ALPHA, 1f).setDuration(100)
        val emailEditTextLayout =
            ObjectAnimator.ofFloat(binding.emailEditTextLayout, View.ALPHA, 1f).setDuration(100)
        val passwordTextView =
            ObjectAnimator.ofFloat(binding.tvPassword, View.ALPHA, 1f).setDuration(100)
        val passwordEditTextLayout =
            ObjectAnimator.ofFloat(binding.passwordEditTextLayout, View.ALPHA, 1f).setDuration(100)
        val signup = ObjectAnimator.ofFloat(binding.btnRegister, View.ALPHA, 1f).setDuration(100)


        AnimatorSet().apply {
            playSequentially(
                title,
                nameTextView,
                nameEditTextLayout,
                usernamenameTextView,
                usernameEditTextLayout,
                emailTextView,
                emailEditTextLayout,
                passwordTextView,
                passwordEditTextLayout,
                signup
            )
            startDelay = 100
        }.start()
    }

}
