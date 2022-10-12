package com.pratyaksh_khurana.SaveNotes.Fragments


import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.pratyaksh_khurana.SaveNotes.R
import com.pratyaksh_khurana.SaveNotes.databinding.FragmentSplashScreenBinding

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
        }, 1000)

        return binding.root
    }

}