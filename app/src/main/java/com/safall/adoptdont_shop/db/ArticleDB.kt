package com.safall.adoptdont_shop.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.safall.adoptdont_shop.dao.ArticleDAO
import com.safall.adoptdont_shop.entity.Article


@Database(
        entities = [(Article::class)],
        version = 1,
        exportSchema = false
)
abstract class ArticleDB:RoomDatabase() {
    abstract fun getArticleDAO() : ArticleDAO

    companion object{
        @Volatile
        private var instance : ArticleDB? = null

        fun getInstance(context : Context) : ArticleDB{
            if(instance == null){
                synchronized(ArticleDB::class){
                    instance = buildDatabase(context)
                }
            }

            return instance!!
        }

        private fun buildDatabase(context : Context) =
                Room.databaseBuilder(
                        context.applicationContext,
                        ArticleDB::class.java,
                        "ArticleDatabase"
                ).build()

    }
}