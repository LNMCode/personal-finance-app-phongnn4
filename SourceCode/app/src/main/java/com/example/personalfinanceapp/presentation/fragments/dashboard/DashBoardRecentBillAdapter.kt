package com.example.personalfinanceapp.presentation.fragments.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.personalfinanceapp.databinding.HeaderDesignBinding
import com.example.personalfinanceapp.databinding.ItemRecentBillDashboardBinding
import com.example.personalfinanceapp.domain.model.bill.Bill
import com.example.personalfinanceapp.domain.model.bill.DailyBill
import java.time.LocalDate

class DashBoardRecentBillAdapter(
    private var dailyBillList: ArrayList<RecentBillsItemModel>,
) : RecyclerView.Adapter<DashBoardRecentBillAdapter.DailyBillsViewHolder>() {

    fun add(list: List<RecentBillsItemModel>) {
        if (dailyBillList.isNotEmpty()) {
            dailyBillList.clear()
        }
        dailyBillList.addAll(dailyBillList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyBillsViewHolder {
        val binding =
            ItemRecentBillDashboardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DailyBillsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dailyBillList.size
    }

    override fun onBindViewHolder(holder: DailyBillsViewHolder, position: Int) {
        val dailyBill = dailyBillList[position]
        holder.bind(dailyBill.bill, dailyBill.date)
    }

    inner class DailyBillsViewHolder(private var binding: ItemRecentBillDashboardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(bill: Bill, date: String?) {
            binding.textView3.text = bill.category
            binding.textView2.text = date.toString()
            binding.textView4.text = bill.cost.toString()
        }

    }

}