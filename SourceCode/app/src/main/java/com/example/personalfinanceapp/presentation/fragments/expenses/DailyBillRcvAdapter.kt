package com.example.personalfinanceapp.presentation.fragments.expenses

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.personalfinanceapp.databinding.HeaderDesignBinding
import com.example.personalfinanceapp.domain.model.bill.DailyBill
import com.example.personalfinanceapp.extensions.StringExtension.toLocalDate

class DailyBillRcvAdapter(
    private val dailyBillList: ArrayList<DailyBill>,
    private val listener: (DailyBill) -> Unit,
) : RecyclerView.Adapter<DailyBillRcvAdapter.DailyBillsViewHolder>() {

    fun addData(list: List<DailyBill>) {
        if (dailyBillList.isNotEmpty()) {
            dailyBillList.clear()
        }
        dailyBillList.addAll(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyBillsViewHolder {
        val binding =
            HeaderDesignBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DailyBillsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dailyBillList.size
    }

    override fun onBindViewHolder(holder: DailyBillsViewHolder, position: Int) {
        holder.bind(dailyBillList[position])
    }

    inner class DailyBillsViewHolder(private var binding: HeaderDesignBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(billList: DailyBill) {
            with(binding) {
                val localDate = billList.date!!.toLocalDate()
                tvDate.text = localDate.dayOfMonth.toString()
                tvDaysOfTheWeek.text = localDate.toString()
                tvMonthAndYear.text =
                    localDate.month.toString() + " " + localDate.year.toString()
                tvTotalMoney.text = "$" + billList.bills!!.sumOf { it.cost!!.toLong() }
                clHeader.setOnClickListener {
                    listener(billList)
                }
            }
        }

    }

}