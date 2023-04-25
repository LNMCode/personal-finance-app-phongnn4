package com.example.personalfinanceapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.personalfinanceapp.databinding.ScrollingMenuItemBinding
import com.example.personalfinanceapp.domain.model.FinancialFunction

class ScrollingHorizontalMenuAdapter(
    private val financialFunctionList: List<FinancialFunction>,
    private val listener: (FinancialFunction) -> Unit,
): RecyclerView.Adapter<ScrollingHorizontalMenuAdapter.ItemsViewHolder>() {

    inner class ItemsViewHolder(private val binding: ScrollingMenuItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(financialFunction: FinancialFunction, listener: (FinancialFunction) -> Unit) {
            binding.apply {
                imvIconMenuItem.setImageResource(financialFunction.iconFunc)
                tvItemName.text = financialFunction.titleFunc
                llItemOfMenu.setOnClickListener { listener(financialFunction) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsViewHolder {
        val binding = ScrollingMenuItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return financialFunctionList.size
    }

    override fun onBindViewHolder(holder: ItemsViewHolder, position: Int) {
        val financialFunction = financialFunctionList[position]
        holder.bind(financialFunction, listener)
    }

}