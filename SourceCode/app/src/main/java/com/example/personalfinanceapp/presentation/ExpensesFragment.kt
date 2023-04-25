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
import com.example.personalfinanceapp.databinding.FragmentExpensesBinding
import com.example.personalfinanceapp.entities.bill.Bill
import com.example.personalfinanceapp.entities.bill.DailyBill
import com.example.personalfinanceapp.entities.category.BillsCategory
import com.example.personalfinanceapp.entities.category.Type.*
import com.example.personalfinanceapp.presentation.adapter.DailyBillRcvAdapter
import java.time.LocalDate
import java.util.ArrayList

class ExpensesFragment : Fragment() {

    private lateinit var binding: FragmentExpensesBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_expenses, container, false)

        navToFragments(binding)

        initRecyclerViewBills(binding)

        return binding.root
    }

    private fun initRecyclerViewBills(binding: FragmentExpensesBinding?) {
        val recyclerView = binding?.rcvRecentBills
        recyclerView?.layoutManager = LinearLayoutManager(requireContext())
        val adapter = DailyBillRcvAdapter(getDailyBillLists()) { selectedItem ->

            val billList: ArrayList<Bill> = selectedItem.billList
            val billTime =
                "${selectedItem.date.dayOfWeek}, ${selectedItem.date.month} ${selectedItem.date.dayOfMonth}, ${selectedItem.date.year}"
            val billTotal = "Total: $${selectedItem.amount}"

            val bundle = Bundle().apply {
                putParcelableArrayList("billList", billList)
                putString("billTime", billTime)
                putString("billTotal", billTotal)
            }
            findNavController().navigate(R.id.action_homeFragment_to_billsListFragment, bundle)
        }
        recyclerView?.adapter = adapter
    }

    private fun getDailyBillLists(): List<DailyBill> {
        val billList1 = arrayListOf<Bill>(
            Bill(1, BillsCategory.getDefaultType(RENTING_HOUSE), 10000, "April rent"),
            Bill(2, BillsCategory.getDefaultType(ELECTRIC), 5000, "Electricity bill"),
            Bill(
                3,
                BillsCategory.getDefaultType(FOOD),
                2000,
                "Weekly grocery shopping"
            )
        )
        val dailyBillList1 = DailyBill(1, LocalDate.of(2023, 4, 17), billList1, 0)

        val billList2 = arrayListOf<Bill>(
            Bill(
                4,
                BillsCategory.getDefaultType(TRANSPORT),
                1500,
                "Metro card refill"
            ),
            Bill(5, BillsCategory.getDefaultType(WATER), 3000, "Water bill"),
            Bill(6, BillsCategory.getDefaultType(ENTERTAINMENT), 2000, "Movie tickets")
        )
        val dailyBillList2 = DailyBill(2, LocalDate.of(2023, 4, 16), billList2, 0)

        val billList3 = arrayListOf<Bill>(
            Bill(
                7,
                BillsCategory.getDefaultType(FOOD),
                3500,
                "Monthly grocery shopping"
            ),
            Bill(8, BillsCategory.getDefaultType(GAS), 4000, "Gas bill"),
            Bill(
                9,
                BillsCategory.getDefaultType(OTHER),
                1000,
                "Birthday gift for a friend"
            )
        )
        val dailyBillList3 = DailyBill(3, LocalDate.of(2023, 4, 15), billList3, 0)

        val billList4 = arrayListOf<Bill>(
            Bill(10, BillsCategory.getDefaultType(RENTING_HOUSE), 10000, "April rent"),
            Bill(11, BillsCategory.getDefaultType(ELECTRIC), 6000, "Electricity bill"),
            Bill(12, BillsCategory.getDefaultType(TRANSPORT), 3000, "Gas refill")
        )
        val dailyBillList4 = DailyBill(4, LocalDate.of(2023, 4, 14), billList4, 0)

        val billList5 = arrayListOf<Bill>(
            Bill(13, BillsCategory.getDefaultType(TV), 4000, "Concert tickets"),
            Bill(
                14,
                BillsCategory.getDefaultType(FOOD),
                2500,
                "Weekly grocery shopping"
            ),
            Bill(15, BillsCategory.getDefaultType(ENTERTAINMENT), 1500, "Online shopping")
        )
        val dailyBillList5 = DailyBill(5, LocalDate.of(2023, 4, 13), billList5, 0)

        // Printing the list of daily bill lists
        return listOf(
            dailyBillList1,
            dailyBillList2,
            dailyBillList3,
            dailyBillList4,
            dailyBillList5
        )
    }

    private fun navToFragments(binding: FragmentExpensesBinding?) {
        binding?.apply {

            imgBack.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_dashBoardFragment)
            }

            btnAddExpense.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_paymentFragment)
            }

            btnAddIncome.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_paymentFragment)
            }

            bottomNavBar.setOnItemSelectedListener { item ->
                when(item.itemId) {
                    R.id.btn_nav_account -> {
                        findNavController().navigate(R.id.action_homeFragment_to_kycFragment)
                        true
                    }
                    R.id.btn_nav_home -> {
                        findNavController().navigate(R.id.action_homeFragment_to_dashBoardFragment)
                        true
                    }
                    R.id.btn_nav_add -> {
                        findNavController().navigate(R.id.action_homeFragment_to_paymentFragment)
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
        binding.bottomNavBar.menu.findItem(R.id.btn_nav_expenses).isChecked = true
    }
}