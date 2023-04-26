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
import com.google.android.material.bottomnavigation.BottomNavigationView
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
        hideBottomNavigation()
        initRecyclerView()
        navToFragments()
    }

    private fun navToFragments() {
        binding.imgBack.setOnClickListener {
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
        val adapter = billList?.let { BillsAdapter(it) }
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
}