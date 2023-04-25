package com.example.personalfinanceapp.base

import android.util.Log
import com.example.personalfinanceapp.domain.model.UserModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import retrofit2.Response

abstract class BaseUseCase {

    protected suspend fun <T : Any> handleResponseFlow(call: suspend () -> Flow<Response<T>>): Flow<T> {
        val dispatcher = Dispatchers.IO
        var response: Response<T>? = null
        try {
            call.invoke().flowOn(dispatcher).collect { response = it }
        } catch (t: Throwable) {
            Log.d("Error tag", "$call: notSuccessful")
            throw parseError("${t.message}")
        }

        return flow {
            if (response?.isSuccessful == true) {
                if (response?.body() == null) {
                    throw parseError("Response without body")
                } else {
                    emit(response?.body()!!)
                }
            } else {
                throw parseError(response?.message() ?: "Error not response")
            }
        }.flowOn(dispatcher)
    }

    protected suspend fun <T : Any> handleFlow(call: suspend () -> Flow<T>): Flow<T> {
        val dispatcher = Dispatchers.IO
        var result: T? = null
        try {
            call.invoke().flowOn(dispatcher).collect { result = it }
        } catch (t: Throwable) {
            Log.d("Error tag", "$call: notSuccessful: ${t.message}")
            throw parseError("${t.message}")
        }
        return flow {
            if (result == null) {
                throw parseError("handleFlow error not response")
            } else {
                emit(result!!)
            }
        }.flowOn(dispatcher)
    }

    private fun parseError(message: String) = Exception(message)
}