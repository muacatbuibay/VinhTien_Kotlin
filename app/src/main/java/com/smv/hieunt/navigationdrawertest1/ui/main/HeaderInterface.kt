package com.smv.hieunt.navigationdrawertest1.ui.main

import androidx.lifecycle.LiveData
import com.smv.hieunt.navigationdrawertest1.data.model.Customer

interface HeaderInterface {
    fun onClick(customer: LiveData<Customer>)
}