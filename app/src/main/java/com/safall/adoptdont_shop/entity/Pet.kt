package com.safall.adoptdont_shop.entity

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Pet(
        @PrimaryKey()
        val _id : String,
        var pet_name : String? =null,
        var pet_species : String? =null,
        var pet_gender : String? = null,
        var pet_img : String? =null,
        var pet_bd : String? =null,
        var pet_fee : String? =null,
        var pet_location : String? =null,
        var pet_contactno : String? =null,
        var pet_info : String? =null,
)
