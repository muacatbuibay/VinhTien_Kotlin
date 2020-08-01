package com.smv.hieunt.navigationdrawertest1.ui.shoppingcart

import android.text.Editable
import android.text.TextWatcher
import androidx.databinding.DataBindingUtil
import com.smv.hieunt.navigationdrawertest1.R
import com.smv.hieunt.navigationdrawertest1.databinding.InputLayoutRowSizeGroupBinding
import com.smv.hieunt.navigationdrawertest1.databinding.ItemCartedProductBinding
import com.smv.hieunt.navigationdrawertest1.ui.productdetail.model.CartedProduct
import com.smv.hieunt.navigationdrawertest1.utils.Common
import com.xwray.groupie.databinding.BindableItem

class CartedProductItem(
    private val cartedProduct: CartedProduct
) : BindableItem<ItemCartedProductBinding>() {

    override fun getLayout(): Int = R.layout.item_carted_product

    override fun bind(viewBinding: ItemCartedProductBinding, position: Int) {
        viewBinding.cartedproduct = cartedProduct
//        for (pos in cartedProduct.quantityList.indices){
//            generateSizeGroup(pos)
//        }
        viewBinding.delete.setOnClickListener {
            Common.listCartedProducts.removeAt(position)
            Common.liveCartedProducts.value = Common.listCartedProducts
        }

    }

//    private fun generateSizeGroup(pos: Int) {
//
//        val quantityBlock = DataBindingUtil.inflate<InputLayoutRowSizeGroupBinding>(layoutInflater,
//            R.layout.input_layout_row_size_group,
//            null, false)
//        val view = quantityBlock.root
//        quantityBlock.txtName.text = sizeList[pos]
//        var quantity = 0
//        quantityBlock.txtQtt.addTextChangedListener(object : TextWatcher {
//            override fun afterTextChanged(p0: Editable?) {
//                quantity = if (quantityBlock.txtQtt.text.isNotEmpty()){
//                    p0.toString().toInt()
//                } else{
//                    0
//                }
//                quantityList[pos] = quantity
//                total = calculateQuantity(quantityList)
//                binding.txtSum.text = (total*1000*goodsGroupId.toInt()).toString()
//            }
//            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
//            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
//        })
//        quantityBlock.btnAdd.setOnClickListener {
//            quantity++
//            quantityBlock.txtQtt.setText(quantity.toString())
//        }
//        quantityBlock.btnRemove.setOnClickListener {
//            if (quantity > 0){
//                quantity--
//                if (quantity == 0){
//                    quantityBlock.txtQtt.setText("")
//                }
//                else{
//                    quantityBlock.txtQtt.setText(quantity.toString())
//                }
//            }
//        }
//
//    }
}