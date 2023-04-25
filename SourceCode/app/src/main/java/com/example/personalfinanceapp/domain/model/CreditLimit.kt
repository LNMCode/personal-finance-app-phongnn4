package com.example.personalfinanceapp.domain.model

data class CreditLimit(
    val money: Long? = null,
    val note: String? = null,
    val type: String? = null,
    val startFrom: String? = null,
)