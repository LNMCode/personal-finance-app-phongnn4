package com.example.personalfinanceapp.presentation.fragments.kyc

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.personalfinanceapp.R
import com.example.personalfinanceapp.constants.Constants
import com.example.personalfinanceapp.databinding.FragmentKycBinding
import com.google.android.material.button.MaterialButton
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class KycFragment : Fragment() {

    private lateinit var binding: FragmentKycBinding

    private val kycViewModel: KycViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentKycBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        backToDashBoardFragment()
        logout()
        registerLogoutEvent()
        registerFetchUser()
        setDateInput()
        navToHomeFragment(binding.btnSubmit)
    }

    private fun registerFetchUser() {
        kycViewModel.userModelLiveData.observe(viewLifecycleOwner) {
            binding.edtAddressInput.setText(it.address)
            binding.edtPhoneInput.setText(it.mobile)
            binding.edtNameInput.setText(it.name)
            binding.edtAccountIdInput.setText(it.id)
            binding.edtDateOfBirthInput.setText(it.DOB)
        }
    }

    private fun navToHomeFragment(btnSubmit: MaterialButton) {
        btnSubmit.setOnClickListener {
            AlertDialog.Builder(requireContext())
                .setTitle("Submit")
                .setMessage("Are you sure that you want to submit?")
                .setPositiveButton("Yes") { _, _ ->
                    handleUpdateInformation()
                    Toast.makeText(requireContext(), "Submitted!", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_kycFragment_to_dashBoardFragment)
                }
                .setNegativeButton("No") { _, _ ->
                    Toast.makeText(requireContext(), "Not submitted!", Toast.LENGTH_SHORT).show()
                }
                .create()
                .show()
        }
    }

    private fun handleUpdateInformation() {
        val name = binding.edtNameInput.text.toString()
        val mobile = binding.edtPhoneInput.text.toString()
        val DOB = binding.edtDateOfBirthInput.text.toString()
        val address = binding.edtAddressInput.text.toString()
        kycViewModel.updateUser(name, mobile, DOB, address)
    }

    private fun setDateInput() {
        binding.edtDateOfBirthInput.setOnClickListener {
            showDatePickerDialog()
        }
    }

    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val datePickerDialog = DatePickerDialog(
            requireContext(),
            { _, year, monthOfYear, dayOfMonth ->
                val selectedDate = Calendar.getInstance()
                selectedDate.set(Calendar.YEAR, year)
                selectedDate.set(Calendar.MONTH, monthOfYear)
                selectedDate.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.US)
                binding.edtDateOfBirthInput.setText(sdf.format(selectedDate.time))
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.show()
    }

    private fun logout() {
        binding.imgLogout.setOnClickListener {
            kycViewModel.logOut()
        }
    }

    private fun registerLogoutEvent() {
        kycViewModel.isLogOuted.observe(viewLifecycleOwner) {
            if (it) {
                Constants.navToLoginActivity(requireContext())
            }
        }
    }

    private fun backToDashBoardFragment() {

    }
}