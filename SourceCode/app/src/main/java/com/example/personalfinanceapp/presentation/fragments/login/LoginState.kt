package com.example.personalfinanceapp.presentation.fragments.login

sealed class LoginState {
    data class Result(val isDone: Boolean, val message: String?) : LoginState()
}