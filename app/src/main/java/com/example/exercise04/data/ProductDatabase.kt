package com.example.exercise04.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase

@Database(entities = [DBProduct::class], version = 1)
abstract class ProductDatabase: RoomDatabase() {
    abstract fun productDao(): ProductDao?

    companion object {
        private var DB_INSTANCE: ProductDatabase? = null
        @Synchronized
        open fun getDatabase(context: Context): ProductDatabase? {
            if (DB_INSTANCE == null) {
                DB_INSTANCE = databaseBuilder(
                    context.applicationContext,
                    ProductDatabase::class.java,
                    "product_database"
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