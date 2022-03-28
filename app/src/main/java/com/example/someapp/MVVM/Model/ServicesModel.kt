package com.example.someapp.MVVM.Model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "services")
data class ServicesModel (
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val name : String,
    val price : String,
    val edIzm : String
    ) : Parcelable