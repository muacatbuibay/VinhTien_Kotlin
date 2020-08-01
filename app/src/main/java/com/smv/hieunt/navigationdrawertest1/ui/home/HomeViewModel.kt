package com.smv.hieunt.navigationdrawertest1.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.smv.hieunt.navigationdrawertest1.data.repositories.CustomerRepository
import com.smv.hieunt.navigationdrawertest1.data.repositories.ProductRepository
import com.smv.hieunt.navigationdrawertest1.utils.lazyDeferred

class HomeViewModel(
    productRepository: ProductRepository,
    private val customerRepository: CustomerRepository
) : ViewModel() {

    val products by lazyDeferred {
        productRepository.getProducts()
    }

    fun getCustomer() = customerRepository.getCustomer()
}