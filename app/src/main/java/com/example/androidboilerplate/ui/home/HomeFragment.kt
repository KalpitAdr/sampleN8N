package com.example.androidboilerplate.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import com.example.androidboilerplate.R
import com.example.androidboilerplate.databinding.FragmentHomeBinding
import com.example.androidboilerplate.ui.base.BaseFragment
import com.example.androidboilerplate.ui.login.LoginFragment
import com.example.androidboilerplate.utils.FragmentUtils

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater, container, false)
    }

    override fun setupViews() {
        binding.btnLogout.setOnClickListener {
            Toast.makeText(context, "Logout successful!", Toast.LENGTH_SHORT).show()
            
            // Navigate back to LoginFragment
            activity?.let { activity ->
                FragmentUtils.replaceFragment(
                    activity = activity,
                    fragment = LoginFragment(),
                    containerId = R.id.fragmentContainer,
                    tag = "LoginFragment",
                    addToBackStack = false
                )
            }
        }
    }

    override fun observeData() {
        // No data observation needed for this simple fragment
    }
} 