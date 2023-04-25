package com.example.personalfinanceapp.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.personalfinanceapp.R
import com.example.personalfinanceapp.presentation.adapter.ScrollingHorizontalMenuAdapter
import com.example.personalfinanceapp.databinding.FragmentDashBoardBinding
import com.example.personalfinanceapp.entities.FinancialFunction

class DashBoardFragment : Fragment() {

    private lateinit var binding: FragmentDashBoardBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_dash_board, container, false)
        initRecycleView()
        navToFragments()
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        // Set default item is Home
        binding.bottomNavBar.menu.findItem(R.id.btn_nav_home).isChecked = true
    }

    // Bottom Navigation Bar
    private fun navToFragments() {
        binding.bottomNavBar.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.btn_nav_account -> {
                    findNavController().navigate(R.id.action_dashBoardFragment_to_kycFragment)
                    true
                }
                R.id.btn_nav_expenses -> {
                    findNavController().navigate(R.id.action_dashBoardFragment_to_homeFragment)
                    true
                }
                R.id.btn_nav_add -> {
                    findNavController().navigate(R.id.action_dashBoardFragment_to_paymentFragment)
                    true
                }
                else -> {
                    item.isChecked = true
                    false
                }
            }
        }
    }

    private fun initRecycleView() {
        val menuItemList = listOf<FinancialFunction>(
            FinancialFunction(R.drawable.ic_savings, "Savings"),
            FinancialFunction(R.drawable.ic_expense_tracking, "Expenses"),
            FinancialFunction(R.drawable.ic_budget_bottom_bar, "Budgets"),
            FinancialFunction(R.drawable.ic_payment, "Payments"),
            FinancialFunction(R.drawable.ic_report, "Reports")
        )

        // horizontalRecyclerViewAdapter
        val menuRecyclerView = binding.rcvMenu.also { rcv ->
            rcv.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            rcv.adapter = ScrollingHorizontalMenuAdapter(menuItemList) { selectedItem ->
                Toast.makeText(requireContext(), selectedItem.titleFunc, Toast.LENGTH_SHORT).show()
            }
        }
    }

}