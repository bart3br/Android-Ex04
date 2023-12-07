package com.example.exercise04

data class ProductModel(val name: String,
                    val description: String,
                    val prodType: Int,
                    val price: Double,
                    val rating: Float): java.io.Serializable

class ProdType {
    companion object {
        const val FOOD = 0
        const val DRINK = 1
        const val CLEANING = 2
    }
}