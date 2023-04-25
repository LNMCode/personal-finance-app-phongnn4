package com.example.personalfinanceapp.di

import com.example.personalfinanceapp.domain.repo.accuracy.AccuracyRepository
import com.example.personalfinanceapp.domain.repo.accuracy.AccuracyRepositoryImpl
import com.example.personalfinanceapp.domain.repo.user.UserRepository
import com.example.personalfinanceapp.domain.repo.user.UserRepositoryImpl
import com.example.personalfinanceapp.domain.usecases.accuracy.AccuracyUseCase
import com.example.personalfinanceapp.domain.usecases.accuracy.AccuracyUseCaseImpl
import com.example.personalfinanceapp.domain.usecases.user.UserUseCase
import com.example.personalfinanceapp.domain.usecases.user.UserUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class BindApplicationModule {
    @Binds
    @Singleton
    abstract fun bindAccuracyRepository(accuracyRepositoryImpl: AccuracyRepositoryImpl): AccuracyRepository

    @Binds
    @Singleton
    abstract fun bindUserRepository(userRepositoryImpl: UserRepositoryImpl): UserRepository

    @Binds
    @Singleton
    abstract fun bindAccuracyUseCase(accuracyUseCaseImpl: AccuracyUseCaseImpl): AccuracyUseCase

    @Binds
    @Singleton
    abstract fun bindUserUseCase(userUseCaseImpl: UserUseCaseImpl): UserUseCase

}