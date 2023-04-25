package com.example.personalfinanceapp.domain.model.bill

import android.os.Parcelable
import com.example.personalfinanceapp.domain.model.category.Category
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue


@Parcelize
data class Bill(
    val id: Int,
    val category: @RawValue Category,
    val cost: Long,
    val note: String
) : Parcelable

