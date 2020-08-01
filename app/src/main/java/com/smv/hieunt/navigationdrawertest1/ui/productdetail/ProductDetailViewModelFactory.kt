package com.smv.hieunt.navigationdrawertest1.ui.productdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.smv.hieunt.navigationdrawertest1.data.repositories.ProductRepository

class ProductDetailViewModelFactory(
    private val repository: ProductRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ProductDetailViewModel(repository) as T
    }
}