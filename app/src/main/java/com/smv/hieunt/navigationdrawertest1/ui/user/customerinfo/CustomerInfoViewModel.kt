package com.smv.hieunt.navigationdrawertest1.ui.user.customerinfo

import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModel
import com.smv.hieunt.navigationdrawertest1.data.model.Customer
import com.smv.hieunt.navigationdrawertest1.data.repositories.CustomerRepository
import com.smv.hieunt.navigationdrawertest1.ui.main.MainActivity
import com.smv.hieunt.navigationdrawertest1.ui.user.LoginListener
import com.smv.hieunt.navigationdrawertest1.ui.user.SignupActivity
import com.smv.hieunt.navigationdrawertest1.utils.ApiException
import com.smv.hieunt.navigationdrawertest1.utils.Common
import com.smv.hieunt.navigationdrawertest1.utils.Coroutines
import com.smv.hieunt.navigationdrawertest1.utils.NoInternetException

class CustomerInfoViewModel(
    private val repository: CustomerRepository
) : ViewModel() {

    var loginListener: LoginListener? = null

    val customer = repository.getCustomer()

    var name: String? = null

    fun onLogoutClick(view: View){
        Coroutines.main { repository.deleteCustomer() }
        Common.isLoggedIn = false
        Intent(view.context, MainActivity::class.java).also {
            it.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            view.context.startActivity(it)
        }
    }

    fun onSaveChangesClick(view: View){
        Coroutines.main {
            try {
                val customer = Customer("name",null,"1266", "02345698223", name,null,null,null,null,null,null,null,"0",null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null)
//                val authResponse = repository.insertCustomer(Customer("name",null,null, code, name,null,null,null,null,null,null,null,"0",null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null))
                val authResponse = repository.updateCustomer(customer)
                authResponse.message?.let {

                    loginListener?.onFailure(it)

                    if (it == "SUCCESS"){
                        customer.id = authResponse.data
                        loginListener?.onSuccess(customer)
                        repository.saveCustomer(customer)
                    }
                    return@main
                }
            }
            catch (e: ApiException){
                loginListener?.onFailure(e.message!!)
            }
            catch (e: NoInternetException){
                loginListener?.onFailure(e.message!!)
            }
        }
    }
}