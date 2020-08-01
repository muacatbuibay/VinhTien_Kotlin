package com.smv.hieunt.navigationdrawertest1.ui.user.customerinfo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.smv.hieunt.navigationdrawertest1.data.repositories.CustomerRepository
import com.smv.hieunt.navigationdrawertest1.ui.user.LoginViewModel

@Suppress("UNCHECKED_CAST")
class CustomerInfoViewModelFactory (
    private val repository: CustomerRepository
) : ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CustomerInfoViewModel(repository) as T
    }

}