package com.example.personalfinanceapp.presentation.fragments.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.personalfinanceapp.R
import com.example.personalfinanceapp.constants.Constants.Companion.navToMainActivity
import com.example.personalfinanceapp.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
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
            navToMainActivity(requireContext())
            //findNavController().navigate(R.id.action_loginFragment_to_dashBoardFragment)
        }
    }
}