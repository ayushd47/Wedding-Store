package com.safall.adoptdont_shop.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
data class Favorite(
    val _id: String? = null,
    val user_id: String?=null,
    val pet_id: Pet?= null,
){
    @PrimaryKey(autoGenerate = true)
    var favId:Int=0
}
