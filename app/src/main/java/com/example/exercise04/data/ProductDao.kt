package com.example.exercise04.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface ProductDao {
    @Query("SELECT * FROM product_table")
    fun getAll(): MutableList<DBProduct>?

    @Query("DELETE FROM product_table")
    fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(product: DBProduct) : Long

    @Delete
    fun delete(product: DBProduct)

    @Update
    fun update(product: DBProduct)

    @Query("SELECT * FROM product_table WHERE id = :itemId")
    fun getItemById(itemId: Int): DBProduct?
}