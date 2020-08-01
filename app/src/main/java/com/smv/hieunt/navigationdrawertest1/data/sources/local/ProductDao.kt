package com.smv.hieunt.navigationdrawertest1.data.sources.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.smv.hieunt.navigationdrawertest1.data.model.Product

@Dao
interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAllProduct(products: List<Product>)

    @Query("select * from Product")
    fun getProduct() : LiveData<List<Product>>

    @Query("select * from Product where id = :selectedProductId")
    fun getProductById(selectedProductId: String): LiveData<Product>

//    @Query("select * from Product where id = :selectedProductId")
//    fun getProductById(selectedProductId: String): Product
}