package com.example.personalfinanceapp.domain.usecases.accuracy

import com.example.personalfinanceapp.base.BaseUseCase
import com.example.personalfinanceapp.domain.repo.accuracy.AccuracyRepository
import com.example.personalfinanceapp.domain.usecases.accuracy.AccuracyUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AccuracyUseCaseImpl @Inject constructor(
    private val accuracyRepository: AccuracyRepository,
) : BaseUseCase(), AccuracyUseCase {
    override suspend fun signInWithEmailAndPassword(email: String, password: String) = handleFlow {
        accuracyRepository.signInWithEmailAndPassword(email, password)
    }

    override suspend fun signUpWithEmailAndPassword(email: String, password: String) = handleFlow {
        accuracyRepository.signUpWithEmailAndPassword(email, password)
    }

    override suspend fun isLogged(): Flow<Boolean> = handleFlow {
        accuracyRepository.isLogged()
    }

    override suspend fun logout(): Flow<Boolean> = handleFlow {
        accuracyRepository.logout()
    }
}