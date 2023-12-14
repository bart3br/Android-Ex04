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
    var productType: Int = 0

    @ColumnInfo(name = "price")
    var price: Double = 0.0

    @ColumnInfo(name = "rating")
    var rating: Float = 0.0f

    constructor() {}
    constructor(name: String, description: String, prodType: Int, price: Double, rating: Float) {
        this.name = name
        this.description = description
        this.productType = prodType
        this.price = price
        this.rating = rating
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as DBProduct

        if (id != other.id) return false
        if (name != other.name) return false
        if (description != other.description) return false
        if (productType != other.productType) return false
        if (price != other.price) return false
        if (rating != other.rating) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + (name?.hashCode() ?: 0)
        result = 31 * result + (description?.hashCode() ?: 0)
        result = 31 * result + productType
        result = 31 * result + price.hashCode()
        result = 31 * result + rating.hashCode()
        return result
    }


}