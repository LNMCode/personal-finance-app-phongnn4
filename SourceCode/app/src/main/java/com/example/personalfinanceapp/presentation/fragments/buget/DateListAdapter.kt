package com.example.personalfinanceapp.presentation.fragments.buget

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.personalfinanceapp.databinding.ItemLayoutBudgetBinding
import com.example.personalfinanceapp.databinding.ItemShowCategoryBinding
import com.example.personalfinanceapp.domain.model.category.Category

class DateListAdapter(
    private val categoryList: MutableList<String>,
    private val clicked: OnDateListClicked,
) :
    RecyclerView.Adapter<DateListAdapter.CategoryViewModel>() {

    private var selectedPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewModel {

        val binding =
            ItemLayoutBudgetBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return CategoryViewModel(binding, clicked)
    }

    override fun onBindViewHolder(holder: CategoryViewModel, position: Int) {
        val category = categoryList[position]
        holder.bind(category, position)
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    inner class CategoryViewModel(private val itemBinding: ItemLayoutBudgetBinding, list: OnDateListClicked) :
        RecyclerView.ViewHolder(itemBinding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(category: String, position: Int) {
            with(itemBinding) {
                tvCategoryName.text = category

                // Catch the click event
                clEachCategory.setOnClickListener {
                    if (selectedPosition != position) {
                        notifyItemChanged(selectedPosition)
                        selectedPosition = position
                        clicked.onItemClicked(category)
                        notifyItemChanged(position)
                    } else {
                        selectedPosition = -1
                        notifyItemChanged(position)
                    }
                }
            }
            // Check the selectedPosition is position or not
            if (selectedPosition == position) {
                itemBinding.imgCheck.visibility = View.VISIBLE
            } else {
                itemBinding.imgCheck.visibility = View.GONE
            }
        }
    }
}