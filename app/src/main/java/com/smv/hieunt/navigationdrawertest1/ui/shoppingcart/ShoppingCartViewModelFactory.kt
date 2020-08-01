package com.smv.hieunt.navigationdrawertest1.ui.shoppingcart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.smv.hieunt.navigationdrawertest1.data.repositories.ProductRepository
import com.smv.hieunt.navigationdrawertest1.ui.productdetail.ProductDetailViewModel

class ShoppingCartViewModelFactory (
    private val repository: ProductRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ProductDetailViewModel(repository) as T
    }
}