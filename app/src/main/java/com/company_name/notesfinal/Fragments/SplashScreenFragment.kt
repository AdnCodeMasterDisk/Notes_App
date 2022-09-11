package com.company_name.notesfinal.Fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.company_name.notesfinal.R
import com.company_name.notesfinal.databinding.FragmentSplashScreenBinding
import kotlin.concurrent.fixedRateTimer

class SplashScreenFragment : Fragment() {
    private lateinit var binding: FragmentSplashScreenBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSplashScreenBinding.inflate(layoutInflater, container, false)

        Handler(Looper.getMainLooper()).postDelayed({
            fragmentManager?.beginTransaction()?.apply {
                replace(R.id.fragmentContainer, HomeFragment())
                    .commit()
            }
        }, 2000)

        return binding.root
    }

}