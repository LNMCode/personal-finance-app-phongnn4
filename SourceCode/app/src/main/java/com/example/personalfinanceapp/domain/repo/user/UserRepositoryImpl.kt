package com.example.personalfinanceapp.domain.repo.user

import com.example.personalfinanceapp.constants.Constants
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val db: FirebaseFirestore,
    private val firebaseAuth: FirebaseAuth,
) : UserRepository {

    override suspend fun createUser(userId: String) = flow {
        try {
            val doc = db.collection(Constants.COLLECTION_USER).document(userId)
            val data = hashMapOf(
                Constants.FIELD_DOB to "",
                Constants.FIELD_ADDRESS to "",
                Constants.FIELD_ID to userId,
                Constants.FIELD_MOBILE to "",
                Constants.FIELD_MONEY to 0,
            )
            doc.set(data).await()
            emit(true)
        } catch (e: Exception) {
            emit(false)
        }
    }

    override suspend fun getUserIdCurrent() = flow {
        try {
            val userId = firebaseAuth.currentUser?.uid
            emit(userId!!)
        } catch (e: Exception) {
            emit("")
        }
    }
}