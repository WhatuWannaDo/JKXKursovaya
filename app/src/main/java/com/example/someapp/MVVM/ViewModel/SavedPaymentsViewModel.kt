package com.example.someapp.MVVM.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.someapp.MVVM.Model.SavedPayments
import com.example.someapp.Room.Data.DB.DataBase
import com.example.someapp.Room.Repository.RepositorySavedPayments
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SavedPaymentsViewModel(application: Application) : AndroidViewModel(application) {

    val getAllSavedPayments : LiveData<List<SavedPayments>>
    private val repositorySavedPayments : RepositorySavedPayments

    init {
        val dao = DataBase.getDatabase(application).paymentDAO()
        repositorySavedPayments = RepositorySavedPayments(dao)
        getAllSavedPayments = repositorySavedPayments.getAllSavedPayments
    }

    fun addPayment(savedPayments: SavedPayments){
        viewModelScope.launch(Dispatchers.IO) {
            repositorySavedPayments.addPayment(savedPayments)
        }
    }

    fun deleteAllPayments(){
        viewModelScope.launch(Dispatchers.IO) {
            repositorySavedPayments.deleteAllPayments()
        }
    }

    fun deleteSinglePayment(savedPayments: SavedPayments){
        viewModelScope.launch(Dispatchers.IO) {
            repositorySavedPayments.deleteSinglePayment(savedPayments)
        }
    }

}