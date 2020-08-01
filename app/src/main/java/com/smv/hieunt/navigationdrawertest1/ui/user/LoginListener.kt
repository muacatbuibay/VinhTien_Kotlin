package com.smv.hieunt.navigationdrawertest1.ui.user

import com.smv.hieunt.navigationdrawertest1.data.model.Customer

interface LoginListener {
    fun onSuccess(customerResponse: Customer)
    fun onFailure(msg: String)
}