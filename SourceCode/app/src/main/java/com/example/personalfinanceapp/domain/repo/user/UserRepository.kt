package com.example.personalfinanceapp.domain.repo.user

import com.example.personalfinanceapp.domain.model.UserModel
import com.example.personalfinanceapp.domain.model.bill.Bill
import com.example.personalfinanceapp.domain.model.bill.DailyBill
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun createUser(userId: String): Flow<Boolean>

    suspend fun updateUser(
        userId: String,
        DOB: String, address: String, name: String, mobile: String
    ): Flow<Boolean>

    suspend fun getUserIdCurrent(): Flow<String>

    suspend fun fetchUserInformation(userId: String): Flow<UserModel>

    suspend fun addBill(userId: String, dailyBills: List<DailyBill>): Flow<Boolean>

    suspend fun updateBill(userId: String, dailyBills: List<DailyBill>): Flow<Boolean>

    suspend fun addIncome(userId: String, income: Long): Flow<Boolean>

    suspend fun minusMoney(userId: String, moneyOrigin: Long, moneyMinus: Long): Flow<Boolean>

    suspend fun updateCreditLimit(
        userId: String,
        money: Long,
        note: String,
        type: String,
        startFrom: String
    ): Flow<Boolean>
}