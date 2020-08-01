package com.smv.hieunt.navigationdrawertest1

import android.app.Application
import com.smv.hieunt.navigationdrawertest1.data.preferences.PreferenceProvider
import com.smv.hieunt.navigationdrawertest1.data.repositories.CustomerRepository
import com.smv.hieunt.navigationdrawertest1.data.repositories.ProductRepository
import com.smv.hieunt.navigationdrawertest1.data.sources.local.AppDatabase
import com.smv.hieunt.navigationdrawertest1.data.sources.remote.NetworkConnectionInterceptor
import com.smv.hieunt.navigationdrawertest1.data.sources.remote.VTApi
import com.smv.hieunt.navigationdrawertest1.ui.home.HomeViewModelFactory
import com.smv.hieunt.navigationdrawertest1.ui.main.HeaderViewModelFactory
import com.smv.hieunt.navigationdrawertest1.ui.productdetail.ProductDetailViewModelFactory
import com.smv.hieunt.navigationdrawertest1.ui.user.LoginViewModel
import com.smv.hieunt.navigationdrawertest1.ui.user.LoginViewModelFactory
import com.smv.hieunt.navigationdrawertest1.ui.user.customerinfo.CustomerInfoViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class VTApplication : Application(), KodeinAware {

    override val kodein: Kodein = Kodein.lazy {
        import(androidXModule(this@VTApplication))

        bind() from singleton { NetworkConnectionInterceptor(instance()) }
        bind() from singleton { VTApi(instance()) }
        bind() from singleton { AppDatabase(instance()) }
        bind() from singleton { PreferenceProvider(instance()) }
        bind() from singleton { CustomerRepository(instance(), instance()) }
        bind() from singleton { ProductRepository(instance(), instance(), instance()) }
        bind() from provider { LoginViewModel(instance()) }
        bind() from provider { LoginViewModelFactory(instance()) }
        bind() from provider { CustomerInfoViewModelFactory(instance()) }
        bind() from provider { HeaderViewModelFactory(instance()) }
        bind() from provider { HomeViewModelFactory(instance(), instance()) }
        bind() from provider { ProductDetailViewModelFactory(instance()) }
    }
}