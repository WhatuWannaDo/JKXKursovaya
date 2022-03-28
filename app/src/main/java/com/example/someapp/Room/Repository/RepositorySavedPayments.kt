package com.example.someapp.Room.Repository

import androidx.lifecycle.LiveData
import com.example.someapp.MVVM.Model.SavedPayments
import com.example.someapp.Room.Data.DAO.DAOPayments

class RepositorySavedPayments(private val dao : DAOPayments) {
    val getAllSavedPayments : LiveData<List<SavedPayments>> = dao.getAllSavedPayments()

    suspend fun addPayment(savedPayments: SavedPayments){
        dao.addPayment(savedPayments)
    }

    suspend fun deleteAllPayments(){
        dao.deleteAllPayments()
    }

    suspend fun deleteSinglePayment(savedPayments: SavedPayments){
        dao.deleteSinglePayment(savedPayments)
    }
}