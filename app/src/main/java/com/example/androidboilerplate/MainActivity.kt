package com.example.androidboilerplate

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.androidboilerplate.databinding.ActivityMainBinding
import com.example.androidboilerplate.ui.login.LoginFragment
import com.example.androidboilerplate.utils.FragmentUtils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Start with LoginFragment if no fragment is currently displayed
        if (supportFragmentManager.findFragmentById(R.id.fragmentContainer) == null) {
            FragmentUtils.addFragment(
                activity = this,
                fragment = LoginFragment(),
                containerId = R.id.fragmentContainer,
                tag = "LoginFragment",
                addToBackStack = false
            )
        }
    }
}