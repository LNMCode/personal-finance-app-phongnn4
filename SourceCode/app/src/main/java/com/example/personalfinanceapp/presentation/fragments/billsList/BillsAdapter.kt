package com.example.personalfinanceapp.presentation.fragments.billsList

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.personalfinanceapp.databinding.ItemDesignBinding
import com.example.personalfinanceapp.domain.model.bill.Bill
import com.example.personalfinanceapp.domain.model.category.BillsCategory

class BillsAdapter(private val billList: List<Bill>, private val listener: OnBillListClicked) :
    RecyclerView.Adapter<BillsAdapter.BillViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BillViewHolder {

        val binding = ItemDesignBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BillViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BillViewHolder, position: Int) {
        val bill = billList[position]
        holder.bind(bill)
        holder.itemView.setOnClickListener { listener.onClicked(bill) }
    }

    override fun getItemCount(): Int {
        return billList.size
    }

    inner class BillViewHolder(private val itemBinding: ItemDesignBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(bill: Bill) {
            with(itemBinding) {
                imvExpenseTypeIcon.setImageResource(
                    BillsCategory.getCategoryIconByName(bill.category!!)!!.symbol
                )
                tvCategoryName.text = bill.category
                tvNote.text = bill.note
                tvMoney.text = "$${bill.cost}"
            }
        }
    }

}
