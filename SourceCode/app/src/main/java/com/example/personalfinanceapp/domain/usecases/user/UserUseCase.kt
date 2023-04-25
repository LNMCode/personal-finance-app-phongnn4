package com.example.personalfinanceapp.domain.usecases.user

import kotlinx.coroutines.flow.Flow

interface UserUseCase {
    suspend fun createUser(userId: String) : Flow<Boolean>

    suspend fun getUserIdCurrent() : Flow<String>
}