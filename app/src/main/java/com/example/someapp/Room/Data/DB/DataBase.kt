package com.example.someapp.Room.Data.DB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.someapp.MVVM.Model.SavedPayments
import com.example.someapp.MVVM.Model.ServicesModel
import com.example.someapp.Room.Data.DAO.DAO
import com.example.someapp.Room.Data.DAO.DAOPayments

@Database(entities = [ServicesModel::class, SavedPayments::class], exportSchema = false, version = 2)
abstract class DataBase : RoomDatabase(){
    abstract fun dao() : DAO
    abstract fun paymentDAO() : DAOPayments

    companion object{
        @Volatile
        private var INSTANCE : DataBase? = null

        fun getDatabase(context: Context): DataBase{
            val tempInstance = INSTANCE

            if (tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(context, DataBase::class.java, "JKXDataBase")
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}