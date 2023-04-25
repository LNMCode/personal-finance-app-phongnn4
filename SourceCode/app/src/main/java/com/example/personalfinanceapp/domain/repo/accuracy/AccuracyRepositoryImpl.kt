package com.example.personalfinanceapp.domain.repo.accuracy

import com.example.personalfinanceapp.presentation.fragments.login.LoginState
import com.example.personalfinanceapp.presentation.fragments.signUp.SignUpState
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject


class AccuracyRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
) : AccuracyRepository {
    override suspend fun signInWithEmailAndPassword(email: String, password: String) = flow {
        try {
            firebaseAuth.signInWithEmailAndPassword(email, password).await()
            emit(LoginState.Result(true, null))
        } catch (e: Exception) {
            emit(LoginState.Result(false, e.message))
        }
    }

    override suspend fun signUpWithEmailAndPassword(email: String, password: String) = flow {
        try {
            firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            emit(SignUpState.Result(true, null))
        } catch (e: Exception) {
            emit(SignUpState.Result(false, e.message))
        }
    }

    override suspend fun isLogged(): Flow<Boolean> = flow {
        try {
            if (firebaseAuth.currentUser == null) {
                emit(false)
            } else {
                emit(true)
            }
        } catch (e: Exception) {
            emit(false)
        }
    }

    override suspend fun logout(): Flow<Boolean> = flow {
        firebaseAuth.signOut()
        emit(true)
    }
}