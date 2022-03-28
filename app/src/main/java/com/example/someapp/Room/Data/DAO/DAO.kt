package com.example.someapp.Room.Data.DAO

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.someapp.MVVM.Model.ServicesModel

@Dao
interface DAO {

    @Query("SELECT * FROM services")
    fun getAllServices() : LiveData<List<ServicesModel>>

    @Update
    suspend fun updateService(servicesModel: ServicesModel)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addService(servicesModel: ServicesModel)
}