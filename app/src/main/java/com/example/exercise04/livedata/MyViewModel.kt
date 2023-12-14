package com.example.exercise04.livedata

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.exercise04.data.DBProduct
import kotlinx.coroutines.launch

class MyViewModel (private val repository: Product2Repository) : ViewModel() {
    private val _products = MutableLiveData<List<DBProduct>>()
    val products: LiveData<List<DBProduct>> get() = _products

    init {
        viewModelScope.launch {
            _products.value = repository.getAllProducts()
        }
    }

    fun addProduct(product: DBProduct) = viewModelScope.launch {
        repository.upsertProduct(product)
        _products.value = repository.getAllProducts()
    }

    fun deleteProduct(product: DBProduct) = viewModelScope.launch {
        repository.delete(product)
        _products.value = repository.getAllProducts()
    }

    fun updateProduct(id: Int, name: String, description: String, productType: Int, price: Double, rating: Float) = viewModelScope.launch {
        repository.update(id, name, description, productType, price, rating)
        //repository.update()
        _products.value = repository.getAllProducts()
    }

    fun update(product: DBProduct) = viewModelScope.launch {
        repository.update(product)
        _products.value = repository.getAllProducts()
    }



    /*fun getAllProducts(): MutableList<DBProduct>? = repository.getAllProducts()
    fun insertProduct(product: DBProduct?) = repository.addProduct(product)
    fun deleteProduct(product: DBProduct?) = repository.deleteProduct(product)*/

    /*companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>, extras: CreationExtras): T {
                val application = checkNotNull(extras[APPLICATION_KEY])
                return MyViewModel(application.applicationContext) as T
            }
        }
    }*/
}