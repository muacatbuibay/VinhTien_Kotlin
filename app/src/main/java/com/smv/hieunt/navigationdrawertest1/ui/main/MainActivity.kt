package com.smv.hieunt.navigationdrawertest1.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.jakewharton.threetenabp.AndroidThreeTen
import com.smv.hieunt.navigationdrawertest1.R
import com.smv.hieunt.navigationdrawertest1.data.model.Customer
import com.smv.hieunt.navigationdrawertest1.databinding.ActivityMainBinding
import com.smv.hieunt.navigationdrawertest1.databinding.NavHeaderMainBinding
import com.smv.hieunt.navigationdrawertest1.ui.home.HomeFragmentDirections
import com.smv.hieunt.navigationdrawertest1.ui.user.LoginActivity
import com.smv.hieunt.navigationdrawertest1.ui.user.LoginViewModelFactory
import com.smv.hieunt.navigationdrawertest1.ui.user.customerinfo.CustomerInfoActivity
import com.smv.hieunt.navigationdrawertest1.utils.Common
import com.smv.hieunt.navigationdrawertest1.utils.toast
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class MainActivity : AppCompatActivity(),
    HeaderInterface, KodeinAware {

    private lateinit var appBarConfiguration: AppBarConfiguration

    private val factory: HeaderViewModelFactory by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        AndroidThreeTen.init(this)
//        Common.listCartedProducts.value = ArrayList()

        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this,
            R.layout.activity_main
        )
        val headerBinding = DataBindingUtil.inflate<NavHeaderMainBinding>(layoutInflater,
            R.layout.nav_header_main,null, false)
        val headerViewModel = ViewModelProviders.of(this, factory).get(HeaderViewModel::class.java)
        headerBinding.viewmodel = headerViewModel
        headerBinding.lifecycleOwner = this
        headerViewModel.headerInterface = this
        binding.navView.addHeaderView(headerBinding.root)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)


        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)

        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            if (Common.isLoggedIn)
                navController.navigate(R.id.nav_shopping_cart)
            else
                toast("Vui lòng đăng nhập.")
        }

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(setOf(
            R.id.nav_home,
            R.id.nav_slideshow,
            R.id.nav_product_detail
        ), drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
//        navView.inflateHeaderView(R.layout.nav_header_main)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onClick(customer: LiveData<Customer>) {
        customer.observe(this, Observer {
            if(it != null){
                Intent(this, CustomerInfoActivity::class.java).also {
                    startActivity(it)
                }
            }
            else{
                Intent(this, LoginActivity::class.java).also {
                    startActivity(it)
                }
            }
        })
    }

    override val kodein by kodein()
}
