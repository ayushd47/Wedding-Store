package com.safall.adoptdont_shop.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.safall.adoptdont_shop.entity.Pet
@Dao
interface PetDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPet(pet: Pet)

    @Query("SELECT * FROM Pet")
    suspend fun getAllPets():MutableList<Pet>
}