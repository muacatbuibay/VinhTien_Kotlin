package com.smv.hieunt.navigationdrawertest1.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.smv.hieunt.navigationdrawertest1.data.repositories.CustomerRepository
import com.smv.hieunt.navigationdrawertest1.ui.user.LoginViewModel

class HeaderViewModelFactory (
    private val repository: CustomerRepository
) : ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HeaderViewModel(repository) as T
    }

}