package com.example.personalfinanceapp.domain.repo.bill

import com.example.personalfinanceapp.constants.Constants
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class BillRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val db: FirebaseFirestore,
) : BillRepository {

    override suspend fun createBill(amount: Int, category: String, note: String) = flow {
        try {
            db.collection(Constants.COLLECTION_BILL).add(
                hashMapOf(
                    "amount" to amount,
                    "category" to category,
                    "note" to note,
                )
            ).await()
            emit(true)
        } catch (e: Exception) {
            emit(false)
        }
    }
}