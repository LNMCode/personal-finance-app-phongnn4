package com.example.personalfinanceapp.extensions

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Date

object DateTimeExtensions {
    fun LocalDate.toFormat() : String {
        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        return this.format(formatter)
    }
}

object StringExtension {
    fun String.toLocalDate() : LocalDate {
        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        return LocalDate.parse(this, formatter)
    }
}