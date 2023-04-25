package com.example.personalfinanceapp.presentation.fragments.payment

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.personalfinanceapp.databinding.ItemShowCategoryBinding
import com.example.personalfinanceapp.domain.model.category.Category

class CategoryListAdapter(
    private val categoryList: MutableList<Category>,
    private val clicked: OnCategoryListClicked,
) :
    RecyclerView.Adapter<CategoryListAdapter.CategoryViewModel>() {

    private var selectedPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewModel {

        val binding =
            ItemShowCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return CategoryViewModel(binding, clicked)
    }

    override fun onBindViewHolder(holder: CategoryViewModel, position: Int) {
        val category = categoryList[position]
        holder.bind(category, position)
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    inner class CategoryViewModel(private val itemBinding: ItemShowCategoryBinding, list: OnCategoryListClicked) :
        RecyclerView.ViewHolder(itemBinding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(category: Category, position: Int) {
            with(itemBinding) {
                imgIcon.setImageResource(category.symbol)
                tvCategoryName.text = category.categoryName

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