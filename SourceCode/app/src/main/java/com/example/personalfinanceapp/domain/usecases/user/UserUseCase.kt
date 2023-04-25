package com.example.personalfinanceapp.domain.usecases.user

import com.example.personalfinanceapp.domain.model.UserModel
import kotlinx.coroutines.flow.Flow

interface UserUseCase {
    suspend fun createUser(userId: String) : Flow<Boolean>

    suspend fun getUserIdCurrent() : Flow<String>

    suspend fun fetchUserInformation(userId: String) : Flow<UserModel?>
}