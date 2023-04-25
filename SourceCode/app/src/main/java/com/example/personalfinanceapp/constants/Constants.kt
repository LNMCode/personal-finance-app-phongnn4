package com.example.personalfinanceapp.constants

import android.content.Context
import android.content.Intent
import com.example.personalfinanceapp.presentation.activities.MainActivity
import com.example.personalfinanceapp.presentation.activities.SignInUpActivity


interface Constants {
    companion object {
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