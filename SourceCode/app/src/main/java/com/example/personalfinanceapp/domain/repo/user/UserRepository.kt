package com.example.personalfinanceapp.domain.repo.user

import com.example.personalfinanceapp.domain.model.UserModel
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun createUser(userId: String) : Flow<Boolean>

    suspend fun getUserIdCurrent() : Flow<String>

    suspend fun fetchUserInformation(userId: String) : Flow<UserModel>

}