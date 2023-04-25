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

    override suspend fun getUserIdCurrent() = handleFlow {
        userRepository.getUserIdCurrent()
    }

    override suspend fun fetchUserInformation(userId: String) = handleFlow {
        userRepository.fetchUserInformation(userId)
    }

    override suspend fun addBill(userId: String, dailyBills: List<DailyBill>) = handleFlow {
        userRepository.addBill(userId, dailyBills)
    }
}