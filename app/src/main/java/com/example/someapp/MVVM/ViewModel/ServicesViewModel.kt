package com.example.someapp.MVVM.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.someapp.MVVM.Model.ServicesModel
import com.example.someapp.Room.Data.DB.DataBase
import com.example.someapp.Room.Repository.RepositoryServices
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ServicesViewModel(application: Application) : AndroidViewModel(application) {

    val readAllServices : LiveData<List<ServicesModel>>
    private val repository : RepositoryServices

    init {
        val DAO = DataBase.getDatabase(application).dao()
        repository = RepositoryServices(DAO)
        readAllServices = repository.getAllServices
    }
    fun updateService(servicesModel: ServicesModel){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateService(servicesModel)
        }
    }

    fun addService(servicesModel: ServicesModel){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addService(servicesModel)
        }
    }

}