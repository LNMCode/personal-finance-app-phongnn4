package com.example.personalfinanceapp.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.personalfinanceapp.R
import com.example.personalfinanceapp.databinding.FragmentPaymentBinding
import com.example.personalfinanceapp.entities.category.BillsCategory
import com.example.personalfinanceapp.entities.category.Category
import com.example.personalfinanceapp.entities.category.Type.*
import com.example.personalfinanceapp.presentation.adapter.CategoryListAdapter

class PaymentFragment : Fragment() {

    private lateinit var binding: FragmentPaymentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_payment, container, false)
        navToFragments(binding)

        initRecyclerView(binding)

        return binding.root
    }

    private fun initRecyclerView(binding: FragmentPaymentBinding?) {
        binding?.rcvCategory?.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = CategoryListAdapter(createCategoriesList())
        }
    }

    private fun createCategoriesList(): MutableList<Category> {
        return mutableListOf(
            BillsCategory.getDefaultType(FOOD),
            BillsCategory.getDefaultType(TRANSPORT),
            BillsCategory.getDefaultType(RENTING_HOUSE),
            BillsCategory.getDefaultType(WATER),
            BillsCategory.getDefaultType(PHONE),
            BillsCategory.getDefaultType(ELECTRIC),
            BillsCategory.getDefaultType(GAS),
            BillsCategory.getDefaultType(TV),
            BillsCategory.getDefaultType(INTERNET),
            BillsCategory.getDefaultType(ENTERTAINMENT),
            BillsCategory.getDefaultType(INCOME),
            BillsCategory.getDefaultType(OTHER),

            )
    }

    private fun navToFragments(binding: FragmentPaymentBinding?) {
        binding?.apply {
            imgBack.setOnClickListener {
                findNavController().navigate(R.id.action_paymentFragment_to_homeFragment)
            }

            binding.bottomNavBar.setOnItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.btn_nav_home -> {
                        findNavController().navigate(R.id.action_paymentFragment_to_dashBoardFragment)
                        true
                    }
                    R.id.btn_nav_expenses -> {
                        findNavController().navigate(R.id.action_paymentFragment_to_homeFragment)
                        true
                    }
                    R.id.btn_nav_account -> {
                        findNavController().navigate(R.id.action_paymentFragment_to_kycFragment)
                        true
                    }
                    else -> {
                        item.isChecked = true
                        false
                    }
                }
            }

        }
    }

    override fun onStart() {
        super.onStart()
        binding.bottomNavBar.menu.findItem(R.id.btn_nav_add).isChecked = true
    }

}