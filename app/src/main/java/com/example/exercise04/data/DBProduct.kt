package com.example.exercise04.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product_table")
class DBProduct {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
    var name: String? = null
    var description: String? = null

    @ColumnInfo(name = "prod_type")
    var prodType: Int = 0

    @ColumnInfo(name = "price")
    var price: Double = 0.0

    @ColumnInfo(name = "rating")
    var rating: Float = 0.0f

    constructor() {}
    constructor(name: String, description: String, prodType: Int, price: Double, rating: Float) {
        this.name = name
        this.description = description
        this.prodType = prodType
        this.price = price
        this.rating = rating
    }
}