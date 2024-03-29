package com.example.personalfinanceapp.domain.model.bill

import android.os.Parcelable
import com.example.personalfinanceapp.domain.model.category.Category
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue
import java.time.LocalDate


@Parcelize
data class Bill(
    val id: String? = null,
    val category: String? = null,
    val cost: Long? = null,
    val note: String? = null,
) : Parcelable

