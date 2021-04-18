package com.safall.adoptdont_shop.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.safall.adoptdont_shop.dao.PetDAO

import com.safall.adoptdont_shop.entity.Pet


@Database(
        entities = [(Pet::class)],
        version = 1,
        exportSchema = false
)
abstract class PetDB : RoomDatabase(){
    abstract fun getPetDAO() : PetDAO

    companion object{
        @Volatile
        private var instance : PetDB? = null

        fun getInstance(context : Context) : PetDB{
            if(instance == null){
                synchronized(PetDB::class){
                    instance = buildDatabase(context)
                }
            }

            return instance!!
        }

        private fun buildDatabase(context : Context) =
                Room.databaseBuilder(
                        context.applicationContext,
                        PetDB::class.java,
                        "PetDatabase"
                ).build()

    }
}