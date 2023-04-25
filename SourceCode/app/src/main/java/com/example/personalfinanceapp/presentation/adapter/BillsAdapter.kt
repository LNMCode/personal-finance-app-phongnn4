package com.example.personalfinanceapp.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.personalfinanceapp.databinding.ItemDesignBinding
import com.example.personalfinanceapp.entities.bill.Bill

class BillsAdapter(private val billList: List<Bill>) : RecyclerView.Adapter<BillsAdapter.BillViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BillViewHolder {

        val binding = ItemDesignBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BillViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BillViewHolder, position: Int) {
        val bill = billList[position]
        holder.bind(bill)
    }

    override fun getItemCount(): Int {
        return billList.size
    }

    inner class BillViewHolder(private val itemBinding: ItemDesignBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(bill: Bill) {
            with(itemBinding) {
                imvExpenseTypeIcon.setImageResource(bill.category.symbol)
                tvCategoryName.text = bill.category.categoryName
                tvNote.text = bill.note
                tvMoney.text = "$${bill.cost}"
            }
        }
    }

}
