package com.smv.hieunt.navigationdrawertest1.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.smv.hieunt.navigationdrawertest1.ui.productdetail.model.CartedProduct

object Common {
    var selectedProductId: String = "0"
    var isLoggedIn: Boolean = false
    var listCartedProducts = ArrayList<CartedProduct>()
    var liveCartedProducts = MutableLiveData<ArrayList<CartedProduct>>()
    const val CURRENT_USER_ID = 0
    const val KEY_SAVED_AT = "key saved at"
    const val MINIMUM_INTERVAL = 6
}