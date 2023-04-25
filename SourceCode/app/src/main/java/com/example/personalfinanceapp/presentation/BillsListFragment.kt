package com.example.personalfinanceapp.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.personalfinanceapp.R
import com.example.personalfinanceapp.databinding.FragmentBillsListBinding
import com.example.personalfinanceapp.entities.bill.Bill
import com.example.personalfinanceapp.presentation.adapter.BillsAdapter

class BillsListFragment : Fragment() {

    private lateinit var binding: FragmentBillsListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_bills_list, container, false)

        initRecyclerView(binding)

        navToFraments(binding)

        return binding.root
    }

    private fun navToFraments(binding: FragmentBillsListBinding?) {
        binding?.apply {
            imgBack.setOnClickListener {
                findNavController().navigate(R.id.action_billsListFragment_to_homeFragment)
            }
        }
    }

    private fun initRecyclerView(binding: FragmentBillsListBinding?) {
        val billList = requireArguments().getParcelableArrayList<Bill>("billList")
        binding?.apply {
            tvBillTime.text = requireArguments().getString("billTime")
            tvBillTotal.text = requireArguments().getString("billTotal")
        }
        val recyclerView = binding?.rcvBillsList
        recyclerView?.layoutManager = LinearLayoutManager(requireContext())
        val adapter = billList?.let { BillsAdapter(it) }
        recyclerView?.adapter = adapter
    }

}