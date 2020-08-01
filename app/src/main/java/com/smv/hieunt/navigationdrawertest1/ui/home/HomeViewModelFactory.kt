package com.smv.hieunt.navigationdrawertest1.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.smv.hieunt.navigationdrawertest1.data.repositories.CustomerRepository
import com.smv.hieunt.navigationdrawertest1.data.repositories.ProductRepository

@Suppress("UNCHECKED_CAST")
class HomeViewModelFactory(
    private val productRepository: ProductRepository,
    private val customerRepository: CustomerRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel(productRepository, customerRepository) as T
    }
}