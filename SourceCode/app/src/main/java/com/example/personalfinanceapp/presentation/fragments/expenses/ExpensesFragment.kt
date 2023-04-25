package com.example.personalfinanceapp.presentation.fragments.expenses

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.personalfinanceapp.R
import com.example.personalfinanceapp.databinding.FragmentExpensesBinding
import com.example.personalfinanceapp.domain.model.bill.Bill
import com.example.personalfinanceapp.domain.model.bill.DailyBill
import com.example.personalfinanceapp.domain.model.category.BillsCategory
import com.example.personalfinanceapp.domain.model.category.Type.*
import com.example.personalfinanceapp.presentation.fragments.dashboard.DashBoardRecentBillAdapter
import com.example.personalfinanceapp.presentation.fragments.dashboard.RecentBillsItemModel
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate
import java.util.ArrayList

@AndroidEntryPoint
class ExpensesFragment : Fragment() {

    private lateinit var binding: FragmentExpensesBinding

    private val expensesViewModel: ExpensesViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentExpensesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetchUserInformation()
        observerUserInformation()
        navToFragments()
    }

    private fun initRecyclerViewBills(dailyBill: List<DailyBill>) {
        binding.rcvRecentBills.layoutManager = LinearLayoutManager(requireContext())
        val adapter = DailyBillRcvAdapter(dailyBill) { selectedItem ->
            val billList: ArrayList<Bill> = selectedItem.bills!!
            val billTime = selectedItem.date
            val billTotal = "Total: ${billList.sumOf { it.cost!! }}"

            val bundle = Bundle().apply {
                putParcelableArrayList("billList", billList)
                putString("billTime", billTime)
                putString("billTotal", billTotal)
            }
            findNavController().navigate(R.id.action_homeFragment_to_billsListFragment, bundle)
        }
        binding.rcvRecentBills.adapter = adapter
    }

    private fun navToFragments() {
        binding.apply {

            binding.imgBack.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_dashBoardFragment)
            }

            btnAddExpense.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_paymentFragment)
            }

            btnAddIncome.setOnClickListener {
                findNavController().navigate(R.id.action_expensesFragment_to_incomeFragment)

            }
        }
    }

    private fun fetchUserInformation() {
        expensesViewModel.fetchUser()
    }

    @SuppressLint("SetTextI18n")
    private fun observerUserInformation() {
        expensesViewModel.userModelLiveData.observe(viewLifecycleOwner) {
            binding.tvBalanceMoney.text = "$${it.money.toString()}"
            it.dailyBills?.let { dailyBill ->
                setUpRecycleViewRecentBills(dailyBill)
                initRecyclerViewBills(dailyBill)
            }
        }
    }

    private fun setUpRecycleViewRecentBills(dailyBill: List<DailyBill>) {
        val recentBillsModel = arrayListOf<RecentBillsItemModel>()
        dailyBill.forEach { dBill ->
            dBill.bills!!.forEach { bill ->
                recentBillsModel.add(RecentBillsItemModel(bill, dBill.date!!))
            }
        }
        val adapter = DailyBillRcvAdapter(dailyBill, {})
        binding.rcvRecentBills.adapter = adapter
    }
}