package com.company_name.notesfinal

import android.os.Bundle
import android.text.TextUtils.replace
import android.view.KeyEvent
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.input.key.Key.Companion.Home
import androidx.core.view.isVisible
import androidx.navigation.NavController
import com.company_name.notesfinal.Fragments.HomeFragment
import com.company_name.notesfinal.Fragments.SplashScreenFragment
import com.company_name.notesfinal.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    //enable view binding
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //hide action bar
        supportActionBar?.hide()

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentContainer, SplashScreenFragment())
                .commit()
        }
    }

    override fun onBackPressed() {

    }
}