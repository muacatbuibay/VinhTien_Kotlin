package com.smv.hieunt.navigationdrawertest1.ui.user

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.smv.hieunt.navigationdrawertest1.R
import com.smv.hieunt.navigationdrawertest1.data.model.Customer
import com.smv.hieunt.navigationdrawertest1.databinding.ActivityLoginBinding
import com.smv.hieunt.navigationdrawertest1.ui.main.MainActivity
import com.smv.hieunt.navigationdrawertest1.ui.user.customerinfo.CustomerInfoActivity
import com.smv.hieunt.navigationdrawertest1.utils.Common
import com.smv.hieunt.navigationdrawertest1.utils.toast
import org.kodein.di.android.kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance

class LoginActivity : AppCompatActivity(), KodeinAware, LoginListener {

    private val factory: LoginViewModelFactory by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityLoginBinding>(this, R.layout.activity_login)
        val viewModel = ViewModelProviders.of(this, factory).get(LoginViewModel::class.java)
        binding.viewmodel = viewModel
        viewModel.loginListener = this

//        viewModel.getLoggedinCustomer().observe(this, Observer { customer ->
//            if (customer != null){
//                Intent(this, CustomerInfoActivity::class.java).also {
//                    it.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
////                    viewModel.saveCustomerInfo(customer)
//                    startActivity(it)
//                }
//            }
//        })

    }

    override val kodein by kodein()

    override fun onSuccess(customerResponse: Customer) {
        toast("Welcome user " + customerResponse.id.toString() )
        Intent(this, MainActivity::class.java).also {
            it.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(it)
        }
    }

    override fun onFailure(msg: String) {
        toast(msg)
    }
}
