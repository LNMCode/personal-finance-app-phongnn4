package com.example.personalfinanceapp.constants

import android.content.Context
import android.content.Intent
import com.example.personalfinanceapp.presentation.activities.MainActivity
import com.example.personalfinanceapp.presentation.activities.SignInUpActivity


interface Constants {
    companion object {
        const val COLLECTION_USER = "user"
        const val FIELD_ID = "id"
        const val FIELD_DOB = "DOB"
        const val FIELD_ADDRESS = "address"
        const val FIELD_CREDIT_LIMIT = "creditLimit"
        const val FIELD_MONEY = "money"
        const val FIELD_NOTE = "note"
        const val FIELD_DAILY_BILLS = "dailyBills"
        const val FIELD_BILLS = "bills"
        const val FIELD_CATEGORY = "category"
        const val FIELD_COST = "cost"
        const val FIELD_DATE = "date"
        const val FIELD_MOBILE = "mobile"
        const val FIELD_NAME = "name"

        fun navToLoginActivity(context: Context) {
            val intent = Intent(context, SignInUpActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            context.startActivity(intent)
        }

        fun navToMainActivity(context: Context) {
            val intent = Intent(context, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            context.startActivity(intent)
        }
    }
}