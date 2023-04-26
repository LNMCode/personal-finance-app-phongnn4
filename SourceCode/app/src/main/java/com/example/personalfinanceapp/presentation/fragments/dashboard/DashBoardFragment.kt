package com.example.personalfinanceapp.presentation.fragments.dashboard

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.personalfinanceapp.R
import com.example.personalfinanceapp.databinding.FragmentDashBoardBinding
import com.example.personalfinanceapp.domain.model.FinancialFunction
import com.example.personalfinanceapp.domain.model.bill.Bill
import com.example.personalfinanceapp.domain.model.bill.DailyBill
import dagger.hilt.android.AndroidEntryPoint
import java.math.BigInteger

@AndroidEntryPoint
class DashBoardFragment : Fragment() {

    private lateinit var binding: FragmentDashBoardBinding

    private val dashBoardViewModel: DashBoardViewModel by viewModels()

    private lateinit var adapter: DashBoardRecentBillAdapter
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
        fetchUserInformation()
        setupRecyclerView()
        observerUserInformation()
    }

    private fun setupRecyclerView() {
        adapter = DashBoardRecentBillAdapter(arrayListOf())
        binding.rcvRecentBills.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.rcvRecentBills.adapter = adapter
    }

    private fun setUpRecycleViewRecentBills(dailyBill: List<DailyBill>) {
        val recentBillsModel = arrayListOf<RecentBillsItemModel>()
        dailyBill.forEach { dBill ->
            dBill.bills!!.forEach { bill ->
                recentBillsModel.add(RecentBillsItemModel(bill, dBill.date!!))
            }
        }
        adapter.add(recentBillsModel)
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

    private fun fetchUserInformation() {
        dashBoardViewModel.fetchUser()
    }

    private fun observerUserInformation() {
        dashBoardViewModel.userModelLiveData.observe(viewLifecycleOwner) { userM ->
            binding.tvMoney.text = userM.money.toString()
            var sumUsing = 0L
            if (userM.dailyBills != null) {
                userM.dailyBills!!.forEach { daiBill ->
                    val billLists: ArrayList<Bill> = daiBill.bills!!
                    sumUsing += billLists.sumOf { it.cost!! }
                    var isOver = false
                    if (userM.creditLimit != null) {
                        isOver = userM.creditLimit!!.money!! < sumUsing
                    }
                    handleColorBudget(isOver)
                    userM.dailyBills?.let { dailyBill ->
                        setUpRecycleViewRecentBills(dailyBill)
                    }
                }
            }
        }
    }

    @SuppressLint("ResourceAsColor")
    private fun handleColorBudget(isOver: Boolean) {
        if (isOver) {
            binding.cvAvailableBalance.setBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.light_red
                )
            )
        } else {
            binding.cvAvailableBalance.setBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.background_line_01
                )
            );
        }
    }
}