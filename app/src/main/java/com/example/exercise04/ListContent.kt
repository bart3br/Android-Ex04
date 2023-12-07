package com.example.exercise04

object Constants {
    private val itemList = mutableListOf<ProductModel>(
        ProductModel("Milk", "Full lactose milk 2%", ProdType.DRINK, 2.50, 3.5f),
        ProductModel("Cola", "Carbonated cola", ProdType.DRINK, 2.20, 4.2f),
        ProductModel("Whole Wheat Bread", "Whole wheat bread", ProdType.FOOD, 3.20, 4.0f),
        ProductModel("Mineral Water", "Mountain mineral water", ProdType.DRINK, 1.50, 4.8f),
        ProductModel("Orange Juice", "Freshly squeezed orange juice", ProdType.DRINK, 3.0, 4.5f),
        ProductModel("Dishwashing Liquid", "Lemon-scented dishwashing liquid", ProdType.CLEANING, 5.80, 4.0f),
        ProductModel("Cleaning Agent", "Universal cleaning agent", ProdType.CLEANING, 4.50, 4.5f),
        ProductModel("Toilet Paper", "10 rolls of toilet paper", ProdType.CLEANING, 2.0, 4.6f),
        ProductModel("Apples", "Fresh Ligol apples", ProdType.FOOD, 1.80, 4.7f),
        ProductModel("Chicken", "Double chicken breast", ProdType.FOOD, 17.50, 4.5f),
        ProductModel("Yogurt", "Strawberry flavoured yogurt", ProdType.DRINK, 5.50, 4.2f),
        ProductModel("Eggs", "Large brown eggs", ProdType.FOOD, 2.00, 2.6f),
        ProductModel("Coffee", "Premium Arabica coffee beans", ProdType.DRINK, 8.50, 4.8f),
        ProductModel("Tomatoes", "Raspberry polish tomatoes", ProdType.FOOD, 2.30, 4.5f),
        ProductModel("Tea", "Herbal tea blend", ProdType.DRINK, 4.20, 4.7f),
        ProductModel("Glass Cleaner", "Streak-free glass cleaner", ProdType.CLEANING, 3.50, 2.3f),
        ProductModel("Bacon", "Smoked bacon without bone", ProdType.FOOD, 5.75, 4.4f),
        ProductModel("Laundry Detergent", "Fragrance-free laundry detergent", ProdType.CLEANING, 7.50, 4.6f),
        ProductModel("Potatoes", "Fresh Idaho potatoes", ProdType.FOOD, 1.10, 3.7f),
    )
    fun getProductsList(): MutableList<ProductModel> {
        val list = ArrayList<ProductModel>()
        for (i in itemList.indices) {
            list.add(itemList[i])
        }
        return list
    }

    fun addProduct(product: ProductModel) {
        itemList.add(product)
    }
}