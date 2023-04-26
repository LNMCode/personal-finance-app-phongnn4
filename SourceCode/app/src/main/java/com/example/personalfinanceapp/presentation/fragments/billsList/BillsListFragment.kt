package com.example.personalfinanceapp.presentation.fragments.billsList

import android.content.ContentValues
import android.content.DialogInterface
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.personalfinanceapp.R
import com.example.personalfinanceapp.databinding.FragmentBillsListBinding
import com.example.personalfinanceapp.domain.model.bill.Bill
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class BillsListFragment : Fragment(), OnBillListClicked {

    private lateinit var binding: FragmentBillsListBinding

    private val billsListViewModel: BillsListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentBillsListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hideBottomNavigation()
        initRecyclerView()
        registerEventUpdateCost()
        navToFragments()
    }

    private fun navToFragments() {
        binding.imgBack.setOnClickListener {
            showBottomNavigation()
            findNavController().popBackStack()
        }
    }

    private fun registerEventUpdateCost() {
        billsListViewModel.isDone.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), "Done!!!", Toast.LENGTH_SHORT).show()
            showBottomNavigation()
            findNavController().popBackStack()
        }
    }

    private fun initRecyclerView() {
        val billList = requireArguments().getParcelableArrayList<Bill>("billList")
        binding.apply {
            tvBillTime.text = requireArguments().getString("billTime")
            tvBillTotal.text = requireArguments().getString("billTotal")
        }
        val recyclerView = binding.rcvBillsList
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val adapter = billList?.let { BillsAdapter(it, this@BillsListFragment) }
        recyclerView.adapter = adapter
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

    override fun onClicked(bill: Bill) {
        val taskEditText = EditText(requireContext())
        val dialog = AlertDialog.Builder(requireContext())
            .setTitle("Edit your cost")
            .setMessage("Enter number cost you want to update")
            .setView(taskEditText)
            .setPositiveButton("Update") { dialog, which ->
                val task = taskEditText.text.toString()
                if (task.isNotBlank()) {
                    billsListViewModel.setCostBill(task, bill)
                }
            }
            .setNegativeButton("Cancel", null)
            .create()
        dialog.show()
    }
}