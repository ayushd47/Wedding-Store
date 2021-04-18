package com.safall.adoptdont_shop.entity

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Product(
        @PrimaryKey()
        val _id : String,
        var product_name : String? =null,
        var product_price : String? =null,
        var product_desc : String? = null,
        var product_img : String? =null,
)
