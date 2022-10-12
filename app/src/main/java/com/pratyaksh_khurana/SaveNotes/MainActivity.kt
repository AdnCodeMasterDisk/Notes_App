package com.pratyaksh_khurana.SaveNotes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pratyaksh_khurana.SaveNotes.Fragments.SplashScreenFragment
import com.pratyaksh_khurana.SaveNotes.databinding.ActivityMainBinding

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