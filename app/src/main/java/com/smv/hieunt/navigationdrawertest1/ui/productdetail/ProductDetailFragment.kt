package com.smv.hieunt.navigationdrawertest1.ui.productdetail

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import org.kodein.di.android.x.kodein

import com.smv.hieunt.navigationdrawertest1.R
import com.smv.hieunt.navigationdrawertest1.databinding.FragmentProductDetailBinding
import com.smv.hieunt.navigationdrawertest1.databinding.InputLayoutRowSizeGroupBinding
import com.smv.hieunt.navigationdrawertest1.ui.main.MainActivity
import com.smv.hieunt.navigationdrawertest1.ui.productdetail.model.CartedProduct
import com.smv.hieunt.navigationdrawertest1.ui.productdetail.model.ItemListSizeProduct
//import com.smv.hieunt.navigationdrawertest1.databinding.InputLayoutRowSizeGroupBinding
import com.smv.hieunt.navigationdrawertest1.utils.Common
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance

class ProductDetailFragment : Fragment(), KodeinAware, ProductDetailListener {

    private val factory: ProductDetailViewModelFactory by instance()

    private lateinit var viewModel: ProductDetailViewModel
    private lateinit var binding: FragmentProductDetailBinding
    var total = 0
    var newTotal = MutableLiveData<Int>()
    private val sizeList = arrayListOf("S","M","L","XL","XXL")
    private var quantityList = mutableListOf<Int>()
    private val listSizeProduct = ArrayList<ItemListSizeProduct>()
    val safeArgs by navArgs<ProductDetailFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_product_detail, container, false)
        initData()
        return binding.root
    }

    private fun initData() {
        viewModel = ViewModelProvider(this, factory).get(ProductDetailViewModel::class.java)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this
        viewModel.productDetailListener = this

        binding.btnBuyNow.setOnClickListener {

        }

        binding.btnAddShopCar.setOnClickListener {
            addToCart()
        }

        binding.btnIphoneBack.setOnClickListener {
            Intent(context, MainActivity::class.java).also {
                startActivity(it)
            }
        }

        if (!Common.isLoggedIn){

            binding!!.quantityField.visibility = View.GONE
            binding!!.txtLabel.visibility = View.GONE
            binding!!.sumBlock.visibility = View.GONE
            binding!!.upperButtonBlock.visibility = View.GONE
            binding!!.btnIphoneBack.setText(R.string.tiep_tuc_xem_hang)
        }
        else{
            binding!!.btnIphoneBack.setText(R.string.tiep_tuc_mua_hang)
        }

        for (position in sizeList.indices){
            quantityList.add(position,0)
            generatesizegroup(position, safeArgs.productPrice)
        }
    }

    private fun addToCart() {
        listSizeProduct.clear()
        for (n in sizeList.indices){
            listSizeProduct.add(ItemListSizeProduct(sizeList[n],quantityList[n]))
        }

        if(total != 0){
            with(Common.listCartedProducts.iterator()){
                forEach {
                    if (it.name == safeArgs.productName){
                        remove()
                    }
                }
            }
            val cartedProduct = CartedProduct(safeArgs.productName, (total*1000*safeArgs.productPrice.toInt()).toString(), listSizeProduct)
            Common.listCartedProducts.add(cartedProduct)
            Toast.makeText(context, "Đã thêm vào giỏ hàng", Toast.LENGTH_SHORT).show()
        }
        else{
            Toast.makeText(context, "Bạn chưa chọn số lượng", Toast.LENGTH_SHORT).show()
        }
    }

    private fun generatesizegroup(pos: Int, productPrice: String){
        val quantityBlock = DataBindingUtil.inflate<InputLayoutRowSizeGroupBinding>(layoutInflater,
            R.layout.input_layout_row_size_group,
            null, false)
        val view = quantityBlock.root
        quantityBlock.txtName.text = sizeList[pos]
        quantityBlock.txtQtt.setText("0")
        var quantity = 0
//        binding.txtSum.text = (total*1000*productPrice.toInt()).toString()
        quantityBlock.txtQtt.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                quantity = if (quantityBlock.txtQtt.text.isNotEmpty()){
                    p0.toString().toInt()
                } else{
                    0
                }
                quantityList[pos] = quantity
                total = calculateQuantity(quantityList)
                newTotal.value = total*1000*productPrice.toInt()
//                binding.txtSum.text = (total*1000*productPrice.toInt()).toString()
            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        })
        quantityBlock.btnAdd.setOnClickListener {
            quantity++
            quantityBlock.txtQtt.setText(quantity.toString())
        }
        quantityBlock.btnRemove.setOnClickListener {
            if (quantity > 0){
                quantity--
                if (quantity == 0){
                    quantityBlock.txtQtt.setText("0")
                }
                else{
                    quantityBlock.txtQtt.setText(quantity.toString())
                }
            }
        }
        binding.quantityField.addView(view)
    }

    private fun calculateQuantity(list: List<Int>): Int {
        var sum = 0
        for (i in list){
            sum += i
        }
        return sum
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//        val safeArgs by navArgs<ProductDetailFragmentArgs>()
//        Toast.makeText(activity, Common.selectedProductId, Toast.LENGTH_SHORT).show()
        newTotal.observe(viewLifecycleOwner, Observer {
            binding.txtSum.text = it.toString()
        })
    }

    override val kodein by kodein()
    override fun showId(id: String) {
        Toast.makeText(activity, id, Toast.LENGTH_SHORT).show()
    }

}
