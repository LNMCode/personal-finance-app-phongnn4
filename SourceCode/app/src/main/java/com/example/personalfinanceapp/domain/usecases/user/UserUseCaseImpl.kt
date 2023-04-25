package com.example.personalfinanceapp.domain.usecases.user

import com.example.personalfinanceapp.base.BaseUseCase
import com.example.personalfinanceapp.domain.model.bill.DailyBill
import com.example.personalfinanceapp.domain.repo.user.UserRepository
import kotlinx.coroutines.flow.Flow

import javax.inject.Inject

class UserUseCaseImpl @Inject constructor(
    private val userRepository: UserRepository,
) : BaseUseCase(), UserUseCase {
    override suspend fun createUser(userId: String) = handleFlow {
        userRepository.createUser(userId)
    }

    override suspend fun updateUser(
        userId: String,
        DOB: String,
        address: String,
        name: String,
        mobile: String
    ) = handleFlow {
        userRepository.updateUser(userId, DOB, address, name, mobile)
    }

    override suspend fun getUserIdCurrent() = handleFlow {
        userRepository.getUserIdCurrent()
    }

    override suspend fun fetchUserInformation(userId: String) = handleFlow {
        userRepository.fetchUserInformation(userId)
    }

    override suspend fun addBill(userId: String, dailyBills: List<DailyBill>) = handleFlow {
        userRepository.addBill(userId, dailyBills)
    }

    override suspend fun addIncome(userId: String, income: Long) = handleFlow {
        userRepository.addIncome(userId, income)
    }

    override suspend fun minusMoney(
        userId: String,
        moneyOrigin: Long,
        moneyMinus: Long
    ) = handleFlow {
        userRepository.minusMoney(userId, moneyOrigin, moneyMinus)
    }

    override suspend fun updateCreditLimit(
        userId: String,
        money: Long,
        note: String,
        type: String,
        startFrom: String,
    ) = handleFlow {
        userRepository.updateCreditLimit(userId, money, note, type, startFrom)
    }
}