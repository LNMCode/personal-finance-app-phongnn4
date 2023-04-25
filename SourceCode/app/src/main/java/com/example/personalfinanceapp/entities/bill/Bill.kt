package com.example.personalfinanceapp.entities.bill

import android.os.Parcelable
import com.example.personalfinanceapp.entities.category.Category
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue


@Parcelize
data class Bill(
    val id: Int,
    val category: @RawValue Category,
    val cost: Long,
    val note: String
) : Parcelable

