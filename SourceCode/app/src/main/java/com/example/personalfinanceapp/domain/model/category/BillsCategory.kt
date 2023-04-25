package com.example.personalfinanceapp.domain.model.category

import com.example.personalfinanceapp.R
import com.example.personalfinanceapp.domain.model.category.Type.*

object BillsCategory {
    private fun getExpenseType(): ArrayList<Category> {
        val categoryList = arrayListOf<Category>().apply {
            add(Category(0, R.drawable.ic_ex_food, "Food"))
            add(Category(1, R.drawable.ic_ex_transport, "Transport"))
            add(Category(2, R.drawable.ic_ex_renting_house, "Renting House"))
            add(Category(3, R.drawable.ic_ex_water_bill, "Water"))
            add(Category(4, R.drawable.ic_ex_phone_bill, "Contact"))
            add(Category(5, R.drawable.ic_ex_electric, "Electricity"))
            add(Category(6, R.drawable.ic_ex_gas_bill, "Gas"))
            add(Category(7, R.drawable.ic_ex_tv_bill, "TV"))
            add(Category(8, R.drawable.ic_ex_internet, "Internet"))
            add(Category(9, R.drawable.ic_ex_entertainment, "Entertainment"))
            add(Category(10, R.drawable.ic_ex_income, "Income"))
            add(Category(11, R.drawable.ic_ex_other_bills, "Other bills"))
        }
        return categoryList
    }

    fun getCategoryIconByName(name: String): Category? {
        val list = getExpenseType()
        return list.find { it.categoryName == name }
    }

    fun getDefaultType(type: Type): Category {
        return when (type) {
            FOOD -> getExpenseType()[0]
            TRANSPORT -> getExpenseType()[1]
            RENTING_HOUSE -> getExpenseType()[2]
            WATER -> getExpenseType()[3]
            PHONE -> getExpenseType()[4]
            ELECTRIC -> getExpenseType()[5]
            GAS -> getExpenseType()[6]
            TV -> getExpenseType()[7]
            INTERNET -> getExpenseType()[8]
            ENTERTAINMENT -> getExpenseType()[9]
            INCOME -> getExpenseType()[10]
            OTHER -> getExpenseType()[11]
        }
    }
}