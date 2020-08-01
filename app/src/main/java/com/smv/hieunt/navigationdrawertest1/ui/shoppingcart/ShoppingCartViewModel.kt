package com.smv.hieunt.navigationdrawertest1.ui.shoppingcart

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.smv.hieunt.navigationdrawertest1.utils.Common

class ShoppingCartViewModel : ViewModel() {

    var mListener: ShoppingCartListener? = null

    fun onTxtPaymentClick(view: View){
        mListener?.zeToast(Common.listCartedProducts.size.toString())
    }
}