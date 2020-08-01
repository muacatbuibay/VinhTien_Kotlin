package com.smv.hieunt.navigationdrawertest1.ui.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.smv.hieunt.navigationdrawertest1.data.repositories.CustomerRepository

class LoginViewModelFactory (
    private val repository: CustomerRepository
) : ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LoginViewModel(repository) as T
    }

}