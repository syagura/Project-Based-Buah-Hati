package com.modul.buahhati.view.splash

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.modul.buahhati.data.remote.LoginPreference
import com.modul.buahhati.data.remote.dataStore
import com.modul.buahhati.view.main.MainActivity
import com.modul.buahhati.view.onboarding.OnBoardingActivity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import com.modul.buahhati.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupView()
        setupAction()
        supportActionBar?.hide()
    }

    private fun setupView(){
        setContentView(R.layout.activity_splash)
        val content = findViewById<View>(android.R.id.content)
        @Suppress("UNUSED_EXPRESSION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            content.viewTreeObserver.addOnDrawListener { false }
        }
    }

    private fun setupAction(){
        val content = findViewById<View>(android.R.id.content)
        @Suppress("UNUSED_EXPRESSION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            content.viewTreeObserver.addOnDrawListener { false }
        }

        Handler(Looper.getMainLooper()).postDelayed({
            val login  = runBlocking {
                LoginPreference.getInstance(this@SplashActivity.dataStore).getLoginStatus().first()
            }
            if(login==true){
                startActivity(Intent(applicationContext, MainActivity::class.java))
                finish()
            }else{
                startActivity(Intent(applicationContext, OnBoardingActivity::class.java))
                finish()
            }
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        },2000L)

    }
}