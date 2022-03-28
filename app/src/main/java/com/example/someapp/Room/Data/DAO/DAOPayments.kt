package com.example.someapp.Room.Data.DAO

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.someapp.MVVM.Model.SavedPayments

@Dao
interface DAOPayments {

    @Query("SELECT * FROM saved_payments")
    fun getAllSavedPayments() : LiveData<List<SavedPayments>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addPayment(savedPayments: SavedPayments)

    @Delete
    suspend fun deleteSinglePayment(savedPayments: SavedPayments)

    @Query("DELETE FROM saved_payments")
    suspend fun deleteAllPayments()

}