package com.example.personalfinanceapp.presentation.fragments.payment

import com.example.personalfinanceapp.domain.model.category.Category

interface OnCategoryListClicked {
    fun onItemClicked(category: Category)
}