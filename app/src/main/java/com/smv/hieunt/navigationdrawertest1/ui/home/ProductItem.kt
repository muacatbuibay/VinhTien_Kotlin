package com.smv.hieunt.navigationdrawertest1.ui.home

import android.view.View
import com.smv.hieunt.navigationdrawertest1.R
import com.smv.hieunt.navigationdrawertest1.data.model.Product
import com.smv.hieunt.navigationdrawertest1.databinding.ItemProductBinding
import com.xwray.groupie.OnItemClickListener
import com.xwray.groupie.OnItemLongClickListener
import com.xwray.groupie.databinding.BindableItem
import com.xwray.groupie.databinding.ViewHolder

class ProductItem(
    private val product: Product
) : BindableItem<ItemProductBinding>() {

    override fun getLayout(): Int = R.layout.item_product

    override fun bind(viewBinding: ItemProductBinding, position: Int) {
        viewBinding.product = product
    }
}