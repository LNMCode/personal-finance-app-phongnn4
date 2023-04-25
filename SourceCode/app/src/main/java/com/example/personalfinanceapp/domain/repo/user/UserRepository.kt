package com.example.personalfinanceapp.domain.repo.user

import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun createUser(userId: String) : Flow<Boolean>

    suspend fun getUserIdCurrent() : Flow<String>

}