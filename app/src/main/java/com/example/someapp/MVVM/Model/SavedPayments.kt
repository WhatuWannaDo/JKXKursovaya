package com.example.someapp.MVVM.Model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "saved_payments")
data class SavedPayments (
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val name : String,
    val price : String,
    val value : String,
    val edinica : String,
    val paymentDate : String
    ): Parcelable