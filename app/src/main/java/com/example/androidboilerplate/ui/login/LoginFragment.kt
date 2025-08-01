package com.example.androidboilerplate.ui.login

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.androidboilerplate.R
import com.example.androidboilerplate.databinding.FragmentLoginBinding
import com.example.androidboilerplate.domain.model.Result
import com.example.androidboilerplate.ui.base.BaseFragment
import com.example.androidboilerplate.ui.home.HomeFragment
import com.example.androidboilerplate.utils.FragmentUtils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>() {

    private val viewModel: LoginViewModel by viewModels()

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentLoginBinding {
        return FragmentLoginBinding.inflate(inflater, container, false)
    }

    override fun setupViews() {
        binding.viewModel = viewModel
        
        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()
            
            if (email.isNotEmpty() && password.isNotEmpty()) {
                viewModel.login(email, password)
            } else {
                Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun observeData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.loginState.collectLatest { result ->
                when (result) {
                    is Result.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                        binding.btnLogin.isEnabled = false
                    }
                    is Result.Success -> {
                        binding.progressBar.visibility = View.GONE
                        binding.btnLogin.isEnabled = true
                        Toast.makeText(context, "Login successful!", Toast.LENGTH_SHORT).show()
                        
                        // Navigate to HomeFragment
                        activity?.let { activity ->
                            FragmentUtils.replaceFragment(
                                activity = activity,
                                fragment = HomeFragment(),
                                containerId = R.id.fragmentContainer,
                                tag = "HomeFragment",
                                addToBackStack = false
                            )
                        }
                    }
                    is Result.Error -> {
                        binding.progressBar.visibility = View.GONE
                        binding.btnLogin.isEnabled = true
                        Toast.makeText(context, result.message, Toast.LENGTH_SHORT).show()
                    }
                    is Result.Idle -> {
                        binding.progressBar.visibility = View.GONE
                        binding.btnLogin.isEnabled = true
                    }
                }
            }
        }
    }
} 