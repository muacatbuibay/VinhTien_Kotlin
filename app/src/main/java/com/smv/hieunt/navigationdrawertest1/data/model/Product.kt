package com.smv.hieunt.navigationdrawertest1.data.model


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Product(
    val defaultSortField: String,
    val action: String?,
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val code: String,
    val name: String,
    val goodsGroupId: String,
    val goodsUnit: String,
    val status: String,
    val description: String?,
    val createdBy: String?,
    val createdDate: String?,
    val lastUpdatedBy: String?,
    val lastUpdatedDate: String?,
    val goodsColor: String?,
    val goodsSize: String?,
    val isSerial: String?,
    val attribute: String?,
    val goodsColorName: String?,
    val goodsSizeName: String?,
    val warehouseType: String,
    val goodsGroupName: String?,
    val lstGoodsPrice: String?,
    val fwmodelId: String
)