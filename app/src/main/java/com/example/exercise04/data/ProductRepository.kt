package com.example.exercise04.data

import android.content.Context

class ProductRepository (context: Context) {
    private var productList: MutableList<DBProduct>? = null
    private var productDao: ProductDao? = null
    private var db: ProductDatabase? = null

    companion object {
        private var INSTANCE: ProductRepository? = null
        fun getInstance(context: Context): ProductRepository? {
            if (INSTANCE == null) {
                synchronized(ProductRepository::class) {
                    INSTANCE = ProductRepository(context)
                }
            }
            return INSTANCE as ProductRepository
        }
    }

    fun getData(): MutableList<DBProduct>? {
        return productDao?.getAll()
    }

    fun addItem(product: DBProduct) : Boolean {
        /*if (productDao?.insert(product) != null)
            return true
        return false*/
        productDao?.insert(product)
        return true
    }

    fun deleteItem(product: DBProduct) : Boolean {
        /*if (productDao?.delete(product) != null)
            return true
        return false*/
        productDao?.delete(product)
        return true
    }

    fun getItemById(itemId: Int): DBProduct? {
        return productDao?.getItemById(itemId)
    }

    fun updateItem(item: DBProduct) : Boolean {
        productDao?.update(item)
        return true
    }

    init {
        db = ProductDatabase.getDatabase(context)!!
        productDao = db?.productDao()!!
        productList = productDao?.getAll()
        //addItem(DBProduct("Bread", "Bread is a staple food prepared from a dough of flour and water, usually by baking.", 0, 1.0, 4.5f))
        //addItem(DBProduct("Milk", "Milk is a nutrient-rich liquid food produced by the mammary glands of mammals.", 1, 2.0, 4.0f))
        //addItem(DBProduct("Soap", "Soap is a salt of a fatty acid used in a variety of cleansing and lubricating products.", 2, 3.0, 3.5f))
    }


}