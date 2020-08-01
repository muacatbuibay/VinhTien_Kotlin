package com.smv.hieunt.navigationdrawertest1.ui.shoppingcart

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.smv.hieunt.navigationdrawertest1.R
import com.smv.hieunt.navigationdrawertest1.databinding.FragmentShoppingCartBinding
import com.smv.hieunt.navigationdrawertest1.ui.main.MainActivity
import com.smv.hieunt.navigationdrawertest1.ui.productdetail.model.CartedProduct
import com.smv.hieunt.navigationdrawertest1.utils.Common
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder

class ShoppingCartFragment : Fragment(), ShoppingCartListener {

    private lateinit var shoppingCartViewModel: ShoppingCartViewModel
    private lateinit var binding: FragmentShoppingCartBinding

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shopping_cart, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Common.liveCartedProducts.value = Common.listCartedProducts
        initData()
    }

    private fun initData() {
        shoppingCartViewModel = ViewModelProviders.of(this).get(ShoppingCartViewModel::class.java)
        binding.viewmodel = shoppingCartViewModel
        binding.lifecycleOwner = this
        shoppingCartViewModel.mListener = this

        Common.liveCartedProducts.observe(viewLifecycleOwner, Observer {
            initRecyclerView(it)
            if (it.isEmpty()){
                binding.txtTotalSum.visibility = View.GONE
                Toast.makeText(activity, "Giỏ hàng trống!", Toast.LENGTH_SHORT).show()
                binding.txtPayment.text = "TIẾP TỤC MUA HÀNG"
                binding.txtPayment.setOnClickListener {
                    Intent(activity, MainActivity::class.java).also {
                        it.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                        startActivity(it)
                    }
                }
            }
            else{
                val totalSum = calculateTotalSum(it)
                binding.txtPayment.text = "MUA NGAY!"
                binding.txtTotalSum.visibility = View.VISIBLE
                binding.txtTotalSum.text = "Tổng tiền: ${totalSum} VND"
            }
        })
    }

    private fun calculateTotalSum(listCartedProduct: ArrayList<CartedProduct>): Int {
        var totalSum = 0
        for(item in listCartedProduct){
            totalSum += item.total.toInt()
        }
        return totalSum
    }

    private fun initRecyclerView(cartedProducts: ArrayList<CartedProduct>) {
        val cartedProductItems = cartedProducts.toCartedProductItem()
        val adapter = GroupAdapter<ViewHolder>().apply {
            addAll(cartedProductItems)
            this.setOnItemClickListener { item, view ->
//                val action = HomeFragmentDirections.actionHomeFragmentToProductDetailFragment(Common.listProducts[getAdapterPosition(item)].id)
//                Common.selectedProductId = Common.listProducts[getAdapterPosition(item)].id
//                view.findNavController().navigate(action)
            }
        }

        binding.listSelectedProducts.apply {
            layoutManager = GridLayoutManager(context,1, GridLayoutManager.VERTICAL, false)
            setHasFixedSize(true)
            this.adapter = adapter
        }
    }

    private fun List<CartedProduct>.toCartedProductItem() : List<CartedProductItem>{
        return this.map {
            CartedProductItem(it)
        }
    }

    override fun zeToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}
