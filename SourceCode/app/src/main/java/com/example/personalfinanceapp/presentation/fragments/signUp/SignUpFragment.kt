package com.example.personalfinanceapp.presentation.fragments.signUp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.personalfinanceapp.R
import com.example.personalfinanceapp.constants.Constants
import com.example.personalfinanceapp.databinding.FragmentSignUpBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment : Fragment() {

    private lateinit var binding: FragmentSignUpBinding

    private val signUpViewModel: SignUpViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setEventLogin()
        setUpEventSignUp()
    }

    private fun setEventLogin() {
        binding.tvLoginNow.setOnClickListener {
            findNavController().navigate(R.id.action_signUpFragment2_to_loginFragment2)
        }
    }

    private fun setUpEventSignUp() {
        binding.btnRegisterAccount.setOnClickListener {
            handleSignUp()
        }
    }

    private fun handleSignUp() {
        val email = binding.edtRegisterEmail.text.toString()
        val password = binding.edtRegisterPassword.text.toString()
        signUpViewModel.signUp(email, password)
        registerSignInObserver()
    }

    private fun registerSignInObserver() {
        signUpViewModel.signUpStateMutableLiveData.observe(viewLifecycleOwner) {
            if (it is SignUpState.Result) {
                if (it.isDone) {
                    Constants.navToMainActivity(requireContext())
                } else {
                    Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}