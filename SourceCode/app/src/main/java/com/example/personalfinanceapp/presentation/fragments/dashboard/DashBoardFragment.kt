package com.example.personalfinanceapp.presentation.fragments.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.personalfinanceapp.R
import com.example.personalfinanceapp.databinding.FragmentDashBoardBinding
import com.example.personalfinanceapp.domain.model.FinancialFunction
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashBoardFragment : Fragment() {

    private lateinit var binding: FragmentDashBoardBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentDashBoardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycleView()
    }

    private fun initRecycleView() {
        val menuItemList = listOf(
            FinancialFunction(R.drawable.ic_savings, "Savings"),
            FinancialFunction(R.drawable.ic_expense_tracking, "Expenses"),
            FinancialFunction(R.drawable.ic_budget_bottom_bar, "Budgets"),
            FinancialFunction(R.drawable.ic_payment, "Payments"),
            FinancialFunction(R.drawable.ic_report, "Reports")
        )

        // horizontalRecyclerViewAdapter
        binding.rcvMenu.also { rcv ->
            rcv.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            rcv.adapter = ScrollingHorizontalMenuAdapter(menuItemList) { selectedItem ->
                handleNavigation(selectedItem.titleFunc)
            }
        }
    }

    private fun handleNavigation(screen: String) {
        when (screen) {
            "Expenses" -> findNavController().navigate(DashBoardFragmentDirections.actionDashBoardFragmentToHomeFragment())
            "Budgets" -> findNavController().navigate(DashBoardFragmentDirections.actionDashBoardFragmentToBudgetFragment())
            "Savings", "Payment" -> Toast.makeText(
                requireContext(),
                screen,
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}