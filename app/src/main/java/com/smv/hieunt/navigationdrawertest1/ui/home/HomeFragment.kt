package com.smv.hieunt.navigationdrawertest1.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.smv.hieunt.navigationdrawertest1.R
import com.smv.hieunt.navigationdrawertest1.data.model.Product
import com.smv.hieunt.navigationdrawertest1.utils.Common
import com.smv.hieunt.navigationdrawertest1.utils.Coroutines
import com.smv.hieunt.navigationdrawertest1.utils.toast
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.selects.select
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class HomeFragment : Fragment(), KodeinAware {

    private lateinit var viewModel: HomeViewModel
    private val factory: HomeViewModelFactory by instance()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, factory).get(HomeViewModel::class.java)
        bindUI()

    }

    private fun bindUI() = Coroutines.main {
        viewModel.products.await().observe(viewLifecycleOwner, Observer {
            initRecyclerView(it)
        })
        viewModel.getCustomer().observe(viewLifecycleOwner, Observer {
            if(it != null){
                Common.isLoggedIn = true
            }
        })
    }

    private fun initRecyclerView(products: List<Product>) {
        val productItems = products.toProductItem()
        val adapter = GroupAdapter<ViewHolder>().apply {
            addAll(productItems)
            this.setOnItemClickListener { item, view ->
                val selectedProduct = products[getAdapterPosition(item)]
                val action = HomeFragmentDirections.actionHomeFragmentToProductDetailFragment(selectedProduct.id, selectedProduct.name, selectedProduct.goodsGroupId)
                Common.selectedProductId = products[getAdapterPosition(item)].id
                view.findNavController().navigate(action)
            }
        }

        recyclerview.apply {
            layoutManager = GridLayoutManager(context,3, GridLayoutManager.VERTICAL, false)
            setHasFixedSize(true)
            this.adapter = adapter
        }

    }

    private fun List<Product>.toProductItem() : List<ProductItem>{
        return this.map {
            ProductItem(it)
        }
    }


    override val kodein by kodein()
}
