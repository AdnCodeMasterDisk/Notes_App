package com.pratyakshkhurana.notes_application

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        //hiding action bar
        supportActionBar?.hide()
        //creates a timer
        Handler(Looper.getMainLooper()).postDelayed(Runnable {
            //code to be executed after 2 sec
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }, 2000)
    }
}