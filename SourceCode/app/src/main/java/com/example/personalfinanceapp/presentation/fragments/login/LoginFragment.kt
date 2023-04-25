package com.example.personalfinanceapp.presentation.fragments.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.personalfinanceapp.R
import com.example.personalfinanceapp.constants.Constants.Companion.navToMainActivity
import com.example.personalfinanceapp.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setEventRegister()
        setEventSignIn()
    }

    private fun setEventRegister() {
        binding.tvRegisterNow.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment2_to_signUpFragment2)
        }
    }

    private fun setEventSignIn() {
        binding.btnSignIn.setOnClickListener {
            handleSignIn()
        }
    }

    private fun handleSignIn() {
        val email = binding.edtUsername.text.toString()
        val password = binding.edtPassword.text.toString()
        loginViewModel.signIn(email, password)
        registerSignInObserver()
    }

    private fun registerSignInObserver() {
        loginViewModel.signInStateMutableLiveData.observe(viewLifecycleOwner) {
            Log.d("####", it.toString())
            if (it is LoginState.Result) {
                if (it.isDone) {
                    navToMainActivity(requireContext())
                } else {
                    Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}