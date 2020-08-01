package com.smv.hieunt.navigationdrawertest1.ui.main

import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModel
import com.smv.hieunt.navigationdrawertest1.data.repositories.CustomerRepository
import com.smv.hieunt.navigationdrawertest1.ui.user.LoginActivity

class HeaderViewModel(
    repository: CustomerRepository
): ViewModel(
) {

    var headerInterface: HeaderInterface? = null
    val customer = repository.getCustomer()

    fun onPicClicked(view: View){

        headerInterface?.onClick(customer)

//        Intent(view.context, LoginActivity::class.java).also {
//            view.context.startActivity(it)
//        }
    }
}