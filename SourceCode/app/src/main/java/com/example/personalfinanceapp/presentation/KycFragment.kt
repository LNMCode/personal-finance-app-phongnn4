package com.example.personalfinanceapp.presentation

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import com.example.personalfinanceapp.R
import com.example.personalfinanceapp.databinding.FragmentKycBinding
import com.google.android.material.button.MaterialButton
import java.text.SimpleDateFormat
import java.util.*

class KycFragment : Fragment() {

    private lateinit var binding: FragmentKycBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_kyc, container, false)
        // Function to back to Dashboard
        backToDashBoardFragment(binding.imgBack)
        // Function to logout
        logout(binding.imgLogout)
        // Function to set date on calendar
        setDateInput(binding.edtDateOfBirthInput)
        // Function to navigate to Home Fragment
        navToHomeFragment(binding.btnSubmit)

        binding.bottomNavBar.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.btn_nav_expenses -> {
                    findNavController().navigate(R.id.action_kycFragment_to_homeFragment)
                    true
                }
                R.id.btn_nav_home -> {
                    findNavController().navigate(R.id.action_kycFragment_to_dashBoardFragment)
                    true
                }
                R.id.btn_nav_add -> {
                    findNavController().navigate(R.id.action_kycFragment_to_paymentFragment)
                    true
                }
                else -> {
                    item.isChecked = true
                    false
                }
            }
        }

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        binding.bottomNavBar.menu.findItem(R.id.btn_nav_account).isChecked = true
    }

    private fun navToHomeFragment(btnSubmit: MaterialButton) {
        btnSubmit.setOnClickListener {
            AlertDialog.Builder(requireContext())
                .setTitle("Submit")
                .setMessage("Are you sure that you want to submit?")
                .setPositiveButton("Yes") { _, _ ->
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


    private fun setDateInput(edtDateOfBirth: AppCompatEditText) {
        edtDateOfBirth.setOnClickListener {
            showDatePickerDialog(edtDateOfBirth)
        }
    }

    private fun showDatePickerDialog(edtDateOfBirth: AppCompatEditText) {
        val calendar = Calendar.getInstance()
        val datePickerDialog = DatePickerDialog(
            requireContext(),
            { _, year, monthOfYear, dayOfMonth ->
                val selectedDate = Calendar.getInstance()
                selectedDate.set(Calendar.YEAR, year)
                selectedDate.set(Calendar.MONTH, monthOfYear)
                selectedDate.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.US)
                edtDateOfBirth.setText(sdf.format(selectedDate.time))
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.show()
    }

    private fun logout(imgLogout: AppCompatImageView) {
        imgLogout.setOnClickListener {
            findNavController().navigate(R.id.action_kycFragment_to_loginFragment)
        }
    }

    private fun backToDashBoardFragment(imgBack: AppCompatImageView) {
        imgBack.setOnClickListener {
            findNavController().navigate(R.id.action_kycFragment_to_dashBoardFragment)
        }
    }
}