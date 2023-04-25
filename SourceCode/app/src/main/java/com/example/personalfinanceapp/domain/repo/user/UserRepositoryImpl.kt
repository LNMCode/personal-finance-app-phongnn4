package com.example.personalfinanceapp.domain.repo.user

import com.example.personalfinanceapp.constants.Constants
import com.example.personalfinanceapp.domain.model.UserModel
import com.example.personalfinanceapp.domain.model.bill.Bill
import com.example.personalfinanceapp.domain.model.bill.DailyBill
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

    override suspend fun fetchUserInformation(userId: String) = flow {
        val result = db.collection(Constants.COLLECTION_USER)
            .document(userId).get().await()
        val userModel = result.toObject(UserModel::class.java)
        emit(userModel!!)
    }

    override suspend fun addBill(userId: String, dailyBills: List<DailyBill>) = flow {
        try {
            val data = hashMapOf(
                Constants.FIELD_DAILY_BILLS to dailyBills
            )
            db.collection(Constants.COLLECTION_USER)
                .document(userId).update(data as Map<String, Any>).await()
            emit(true)
        } catch (e: Exception) {
            emit(false)
        }
    }

    override suspend fun addIncome(userId: String, income: Long) = flow {
        try {
            val data = hashMapOf(
                Constants.FIELD_MONEY to income
            )
            db.collection(Constants.COLLECTION_USER)
                .document(userId).update(data as Map<String, Any>).await()
            emit(true)
        } catch (e: Exception) {
            emit(false)
        }
    }

    override suspend fun minusMoney(
        userId: String,
        moneyOrigin: Long,
        moneyMinus: Long
    ) = flow {
        try {
            val data = hashMapOf(
                Constants.FIELD_MONEY to moneyOrigin - moneyMinus
            )
            db.collection(Constants.COLLECTION_USER)
                .document(userId).update(data as Map<String, Any>).await()
            emit(true)
        } catch (e: Exception) {
            emit(false)
        }
    }

    override suspend fun updateCreditLimit(
        userId: String,
        money: Long,
        note: String,
        type: String,
        startFrom: String,
    ) = flow {
        try {
            val data = hashMapOf(
                Constants.FIELD_CREDIT_LIMIT to hashMapOf(
                    Constants.FIELD_MONEY to money,
                    Constants.FIELD_TYPE to type,
                    Constants.FIELD_NOTE to note,
                    Constants.FIELD_START_FROM to startFrom,
                )
            )
            db.collection(Constants.COLLECTION_USER)
                .document(userId).update(data as Map<String, Any>).await()
            emit(true)
        } catch (e: Exception) {
            emit(false)
        }
    }

}