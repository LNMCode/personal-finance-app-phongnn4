package com.example.personalfinanceapp.presentation.fragments.billsList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.personalfinanceapp.R
import com.example.personalfinanceapp.databinding.FragmentBillsListBinding
import com.example.personalfinanceapp.domain.model.bill.Bill
import com.example.personalfinanceapp.presentation.adapter.BillsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BillsListFragment : Fragment() {

    private lateinit var binding: FragmentBillsListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        binding = FragmentBillsListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        navToFragments()
    }

    private fun navToFragments() {
        binding.apply {
            imgBack.setOnClickListener {
                findNavController().navigate(R.id.action_billsListFragment_to_homeFragment)
            }
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
        val adapter = billList?.let { BillsAdapter(it) }
        recyclerView.adapter = adapter
    }

}