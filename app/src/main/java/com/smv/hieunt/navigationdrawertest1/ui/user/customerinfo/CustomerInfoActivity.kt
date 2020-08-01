package com.smv.hieunt.navigationdrawertest1.ui.user.customerinfo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.smv.hieunt.navigationdrawertest1.R
import com.smv.hieunt.navigationdrawertest1.data.model.Customer
import com.smv.hieunt.navigationdrawertest1.databinding.ActivityCustomerInfoBinding
import com.smv.hieunt.navigationdrawertest1.ui.user.LoginListener
import com.smv.hieunt.navigationdrawertest1.utils.toast
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class CustomerInfoActivity : AppCompatActivity(), KodeinAware, LoginListener {

    private val factory: CustomerInfoViewModelFactory by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityCustomerInfoBinding>(this, R.layout.activity_customer_info)
        val viewmodel = ViewModelProviders.of(this, factory).get(CustomerInfoViewModel::class.java)
        binding.viewmodel = viewmodel
        binding.lifecycleOwner = this
        viewmodel.loginListener = this
    }

    override val kodein by kodein()
    override fun onSuccess(customerResponse: Customer) {
        toast("Update Successful")
    }

    override fun onFailure(msg: String) {
        toast(msg)
    }
}
