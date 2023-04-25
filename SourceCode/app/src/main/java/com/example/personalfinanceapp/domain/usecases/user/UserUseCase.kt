package com.example.personalfinanceapp.domain.usecases.user

import com.example.personalfinanceapp.domain.model.UserModel
import com.example.personalfinanceapp.domain.model.bill.DailyBill
import kotlinx.coroutines.flow.Flow

interface UserUseCase {
    suspend fun createUser(userId: String) : Flow<Boolean>

    suspend fun getUserIdCurrent() : Flow<String>

    suspend fun fetchUserInformation(userId: String) : Flow<UserModel?>

    suspend fun addBill(userId: String, dailyBills: List<DailyBill>): Flow<Boolean>

    suspend fun addIncome(userId: String, income: Long): Flow<Boolean>

    suspend fun minusMoney(userId: String, moneyOrigin: Long, moneyMinus: Long): Flow<Boolean>
}