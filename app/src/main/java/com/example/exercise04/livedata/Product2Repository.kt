package com.example.exercise04.livedata

import android.content.Context
import com.example.exercise04.data.DBProduct
import kotlinx.coroutines.withContext
import kotlinx.coroutines.Dispatchers

class Product2Repository (context: Context) {
    var productList: MutableList<DBProduct>? = null
    lateinit var productDao: Product2Dao
    lateinit var db: Product2Database

    init {
        db = Product2Database.getDatabase(context)!!
        productDao = db.productDao()!!
        //productList = productDao.getAll()
    }

    companion object {
        private var R_INSTANCE: Product2Repository? = null

        fun getInstance(context: Context) : Product2Repository? {
            if (R_INSTANCE == null) {
                R_INSTANCE = Product2Repository(context)
            }
            return R_INSTANCE as Product2Repository
        }
    }

    suspend fun upsertProduct(product: DBProduct) = withContext(Dispatchers.IO) {
        productDao.upsertProduct(product)
    }

    suspend fun getAllProducts(): MutableList<DBProduct>? = withContext(Dispatchers.IO) {
        productDao.getAll()
    }

    suspend fun deleteAll() = withContext(Dispatchers.IO) {
        productDao.deleteAll()
    }

    suspend fun insert(product: DBProduct) = withContext(Dispatchers.IO) {
        productDao.insert(product)
    }

    suspend fun delete(product: DBProduct) = withContext(Dispatchers.IO) {
        productDao.delete(product)
    }

    suspend fun update(id: Int, name: String, description: String, productType: Int, price: Double, rating: Float) = withContext(Dispatchers.IO) {
        productDao.update(id, name, description, productType, price, rating)
    }

    suspend fun update(product: DBProduct) = withContext(Dispatchers.IO) {
        productDao.update(product)
    }

    suspend fun getItemById(itemId: Int): DBProduct? = withContext(Dispatchers.IO) {
        productDao.getItemById(itemId)
    }

}