package com.example.exercise04.livedata

import android.content.Context
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import com.example.exercise04.data.DBProduct

@Database(entities = [DBProduct::class], version = 1)
abstract class Product2Database: RoomDatabase() {
    abstract fun productDao(): Product2Dao?

    companion object {
        @Volatile
        private var DB_INSTANCE: Product2Database? = null
        @Synchronized
        open fun getDatabase(context: Context): Product2Database? {
            if (DB_INSTANCE == null) {
                DB_INSTANCE = databaseBuilder(
                    context.applicationContext,
                    Product2Database::class.java,
                    "product2_database"
                )
                    .allowMainThreadQueries()
                    .build()
            }
            return DB_INSTANCE
        }
        /*fun getDatabase(context: Context): ProductDatabase? {
            val tempInstance = DB_INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = databaseBuilder(
                    context.applicationContext,
                    ProductDatabase::class.java,
                    "product_database"
                ).build()
                DB_INSTANCE = instance
                return instance
            }
        }*/
    }
}