package com.example.exercise04.livedata

import com.example.exercise04.data.DBProduct

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert

@Dao
interface Product2Dao {

    @Upsert
    suspend fun upsertProduct(product: DBProduct)

    @Query("SELECT * FROM product_table")
    suspend fun getAll(): MutableList<DBProduct>?

    @Query("DELETE FROM product_table")
    suspend fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(product: DBProduct) : Long

    @Delete
    suspend fun delete(product: DBProduct)

    @Query("UPDATE product_table SET name = :name, description = :description, prod_type = :type, price = :price, rating = :rating WHERE id = :id")
    suspend fun update(id: Int, name: String?, description: String?, type: Int, price: Double, rating: Float)

    @Update
    suspend fun update(product: DBProduct)

    @Query("SELECT * FROM product_table WHERE id = :itemId")
    suspend fun getItemById(itemId: Int): DBProduct?
}