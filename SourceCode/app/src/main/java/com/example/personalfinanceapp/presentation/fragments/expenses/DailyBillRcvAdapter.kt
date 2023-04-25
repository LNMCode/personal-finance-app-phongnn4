package com.example.personalfinanceapp.presentation.fragments.expenses

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.personalfinanceapp.databinding.HeaderDesignBinding
import com.example.personalfinanceapp.domain.model.bill.DailyBill

class DailyBillRcvAdapter(
    private val dailyBillList: List<DailyBill>,
    private val listener: (DailyBill) -> Unit,
) : RecyclerView.Adapter<DailyBillRcvAdapter.DailyBillsViewHolder>() {

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
                tvDate.text = billList.date!!.dayOfMonth.toString()
                tvDaysOfTheWeek.text = billList.date!!.dayOfWeek.toString()
                tvMonthAndYear.text =
                    billList.date!!.month.toString() + " " + billList.date!!.year.toString()
                tvTotalMoney.text = "$" + billList.amount
                clHeader.setOnClickListener {
                    listener(billList)
                }
            }
        }

    }

}