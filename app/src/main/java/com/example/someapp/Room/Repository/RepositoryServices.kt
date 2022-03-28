package com.example.someapp.Room.Repository

import androidx.lifecycle.LiveData
import com.example.someapp.MVVM.Model.ServicesModel
import com.example.someapp.Room.Data.DAO.DAO

class RepositoryServices(private val DAO : DAO) {

    val getAllServices : LiveData<List<ServicesModel>> = DAO.getAllServices()

    suspend fun updateService(servicesModel: ServicesModel){
        DAO.updateService(servicesModel)
    }

    suspend fun addService(servicesModel: ServicesModel){
        DAO.addService(servicesModel)
    }
}