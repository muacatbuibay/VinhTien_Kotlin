package com.smv.hieunt.navigationdrawertest1.ui.user

import android.content.Intent
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.smv.hieunt.navigationdrawertest1.data.model.Customer
import com.smv.hieunt.navigationdrawertest1.data.repositories.CustomerRepository
import com.smv.hieunt.navigationdrawertest1.utils.ApiException
import com.smv.hieunt.navigationdrawertest1.utils.Coroutines
import com.smv.hieunt.navigationdrawertest1.utils.NoInternetException

class LoginViewModel(
    private val repository: CustomerRepository
): ViewModel() {

    var id: String? = null

    var defaultSortField: String? = null
    var action: String? = null
    var code: String? = null
    var name: String? = null
    var customerType: String? = null
    var address: String? = null
    var phoneNumber: String? = null
    var email: String? = null
    var fax: String? = null
    var description: String? = null
    var agentName: String? = null
    var status: String? = null
    var createdBy: String? = null
    var createdDate: String? = null
    var lastUpdatedBy: String? = null
    var lastUpdatedDate: String? = null
    var province: String? = null
    var district: String? = null
    var commune: String? = null
    var policyPrice: String? = null
    var policyPriceName: String? = null
    var customerGroupId: String? = null
    var locationMap: String? = null
    var departmentId: String? = null
    var policyPriceType: String? = null
    var supId: String? = null
    var isMapDisplay: String? = null
    var fwmodelId: String? = null


    var loginListener: LoginListener? = null

    fun getLoggedinCustomer() = repository.getCustomer()

    fun saveCustomerInfo(customer: Customer) {
//        this.user = customer
    }

    fun onLoginClick(view: View){
        if (id.isNullOrEmpty()){
            loginListener?.onFailure("id is empty")
            return
        }
        Coroutines.main {
            try {
                val customerResponse = repository.getCustomerById(id!!)
                customerResponse.let {
                    loginListener?.onSuccess(it)
                    repository.saveCustomer(it)
                    return@main
                }
            }
            catch (e: ApiException){
                loginListener?.onFailure("fail2")
            }
            catch (e: NoInternetException){
                loginListener?.onFailure("fail3")
            }
        }
    }

    fun onSignupClick(view: View){
        if (code.isNullOrEmpty()){
            loginListener?.onFailure("Main phone numer is emtpy")
            return
        }

        if (name.isNullOrEmpty()){
            loginListener!!.onFailure("name is empty")
            return
        }

        Coroutines.main {
            try {
                val customer = Customer("name",null,null, code, name,null,null,null,null,null,null,null,"0",null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null)
//                val authResponse = repository.insertCustomer(Customer("name",null,null, code, name,null,null,null,null,null,null,null,"0",null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null))
                val authResponse = repository.insertCustomer(customer)
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

    fun toSignup(view: View){
        Intent(view.context, SignupActivity::class.java).also {
            view.context.startActivity(it)
        }
    }

    fun toLogin(view: View){
        Intent(view.context, LoginActivity::class.java).also {
            view.context.startActivity(it)
        }
    }

}