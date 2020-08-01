package com.smv.hieunt.navigationdrawertest1.ui.productdetail

import android.view.View
import android.widget.Toast
import androidx.lifecycle.*
import com.smv.hieunt.navigationdrawertest1.data.model.Product
import com.smv.hieunt.navigationdrawertest1.data.repositories.ProductRepository
import com.smv.hieunt.navigationdrawertest1.utils.Common
import com.smv.hieunt.navigationdrawertest1.utils.Coroutines
import com.smv.hieunt.navigationdrawertest1.utils.lazyDeferred
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.withContext

class ProductDetailViewModel(
    private val repository: ProductRepository
) : ViewModel() {

    var productDetailListener: ProductDetailListener? = null

    val selectedProduct = repository.getProductById(Common.selectedProductId)


}
