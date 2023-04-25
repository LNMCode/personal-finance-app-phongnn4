package com.example.personalfinanceapp.presentation.fragments.buget

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.personalfinanceapp.databinding.FragmentBudgetBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BudgetFragment : Fragment() , OnDateListClicked {

    private lateinit var binding: FragmentBudgetBinding
    
    private val budgetViewModel : BudgetViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentBudgetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        setUpAdd()
    }

    private fun initRecyclerView() {
        binding.rcvDateTime.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = DateListAdapter(createCategoriesList(), this@BudgetFragment)
        }
    }

    private fun setUpAdd() {
        binding.btnAdd.setOnClickListener { handleAddBill() }
    }

    private fun handleAddBill() {
        val amount = binding.edtEnterAmount.text.toString()
        val notes = binding.edtAddNote.text.toString()
        budgetViewModel.updateCreditLimit(requireContext(), amount, notes)
    }

    private fun createCategoriesList(): MutableList<String> {
        return mutableListOf(
            "1 Week",
            "2 Week",
            "3 Week",
            "1 Month",
            "2 Month",
            "3 Month",
            "1 Year",
            "2 Year"
        )
    }

    override fun onItemClicked(date: String) {
        budgetViewModel.setDateType(date)
    }
}