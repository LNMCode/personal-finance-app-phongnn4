package com.example.personalfinanceapp.presentation.fragments.income

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.personalfinanceapp.R
import com.example.personalfinanceapp.databinding.FragmentExpensesBinding
import com.example.personalfinanceapp.databinding.FragmentIncomeBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class IncomeFragment : Fragment() {
    private lateinit var binding: FragmentIncomeBinding

    private val incomeViewModel : IncomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentIncomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hideBottomNavigation()
        observerIncome()
        setUpAddBtn()
        setUpBackBtn()
    }

    private fun setUpAddBtn() {
        binding.btnAdd.setOnClickListener {
            handleAddIncome()
        }
    }

    private fun handleAddIncome() {
        val income = binding.edtEnterAmount.text.toString()
        incomeViewModel.addIncome(income)
    }

    private fun setUpBackBtn() {
        binding.imgBack.setOnClickListener {
            showBottomNavigation()
            findNavController().popBackStack()
        }
    }

    private fun observerIncome() {
        incomeViewModel.incomeLiveData.observe(viewLifecycleOwner) {
            if (it) {
                showBottomNavigation()
                findNavController().popBackStack()
            }
        }
    }

    private fun hideBottomNavigation() {
        val bottomNav = activity?.findViewById<BottomNavigationView>(R.id.bottom_nav_bar)
        if (bottomNav != null) {
            bottomNav.visibility = View.GONE
        }
    }

    private fun showBottomNavigation() {
        val bottomNav = activity?.findViewById<BottomNavigationView>(R.id.bottom_nav_bar)
        if (bottomNav != null) {
            bottomNav.visibility = View.VISIBLE
        }
    }
}