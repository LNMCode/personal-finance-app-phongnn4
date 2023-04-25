package com.example.personalfinanceapp.presentation.fragments.payment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.personalfinanceapp.databinding.FragmentPaymentBinding
import com.example.personalfinanceapp.domain.model.category.BillsCategory
import com.example.personalfinanceapp.domain.model.category.Category
import com.example.personalfinanceapp.domain.model.category.Type.*
import dagger.hilt.android.AndroidEntryPoint
import java.text.FieldPosition

@AndroidEntryPoint
class PaymentFragment : Fragment(), OnCategoryListClicked {

    private lateinit var binding: FragmentPaymentBinding

    private val paymentViewModel: PaymentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentPaymentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        setUpAdd()
    }

    private fun initRecyclerView() {
        binding.rcvCategory.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = CategoryListAdapter(createCategoriesList(), this@PaymentFragment)
        }
    }

    private fun setUpAdd() {
        binding.btnAdd.setOnClickListener { handleAddBill() }
    }

    private fun handleAddBill() {
        val amount = binding.edtEnterAmount.text.toString()
        val notes = binding.edtAddNote.text.toString()
        paymentViewModel.addBill(requireContext(), amount, notes)
    }

    private fun createCategoriesList(): MutableList<Category> {
        return mutableListOf(
            BillsCategory.getDefaultType(FOOD),
            BillsCategory.getDefaultType(TRANSPORT),
            BillsCategory.getDefaultType(RENTING_HOUSE),
            BillsCategory.getDefaultType(WATER),
            BillsCategory.getDefaultType(PHONE),
            BillsCategory.getDefaultType(ELECTRIC),
            BillsCategory.getDefaultType(GAS),
            BillsCategory.getDefaultType(TV),
            BillsCategory.getDefaultType(INTERNET),
            BillsCategory.getDefaultType(ENTERTAINMENT),
            BillsCategory.getDefaultType(INCOME),
            BillsCategory.getDefaultType(OTHER),
        )
    }

    override fun onItemClicked(category: Category) {
        paymentViewModel.setCategory(category)
    }
}