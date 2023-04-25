package com.example.personalfinanceapp.domain.repo.accuracy

import com.example.personalfinanceapp.presentation.fragments.login.LoginState
import com.example.personalfinanceapp.presentation.fragments.signUp.SignUpState
import kotlinx.coroutines.flow.Flow

interface AccuracyRepository {
    suspend fun signInWithEmailAndPassword(email: String, password: String) : Flow<LoginState>

    suspend fun signUpWithEmailAndPassword(email: String, password: String) : Flow<SignUpState>

    suspend fun isLogged(): Flow<Boolean>

    suspend fun logout(): Flow<Boolean>
}