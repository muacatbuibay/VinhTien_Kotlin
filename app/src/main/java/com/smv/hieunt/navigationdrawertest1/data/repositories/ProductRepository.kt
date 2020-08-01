package com.smv.hieunt.navigationdrawertest1.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.smv.hieunt.navigationdrawertest1.data.model.Product
import com.smv.hieunt.navigationdrawertest1.data.preferences.PreferenceProvider
import com.smv.hieunt.navigationdrawertest1.data.sources.local.AppDatabase
import com.smv.hieunt.navigationdrawertest1.data.sources.remote.SafeApiRequest
import com.smv.hieunt.navigationdrawertest1.data.sources.remote.VTApi
import com.smv.hieunt.navigationdrawertest1.utils.Common
import com.smv.hieunt.navigationdrawertest1.utils.Coroutines
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.threeten.bp.LocalDateTime
import org.threeten.bp.temporal.ChronoUnit



class ProductRepository (
    private val api: VTApi,
    private val db: AppDatabase,
    private val prefs: PreferenceProvider
) : SafeApiRequest() {
    private val products = MutableLiveData<List<Product>>()
    val selectedProduct = MutableLiveData<Product>()

    init {
        products.observeForever{
            saveProducts(it)
        }
    }

    suspend fun getProducts(): LiveData<List<Product>> {
        return withContext(Dispatchers.IO){
            fetchProducts()
            db.getProductDao().getProduct()
        }
    }

      fun getProductById(selectedProductId: String): LiveData<Product>{
        return db.getProductDao().getProductById(selectedProductId)
//         return apiRequest {
//             api.getProductById(selectedProductId)
//         }
     }

//    fun getProductById(selectedProductId: String): Product{
//        return db.getProductDao().getProductById(selectedProductId)
//    }



    private suspend fun fetchProducts(){
        val lastSavedAt = prefs.getLastSavedAt()

        if (lastSavedAt == null || isFetchNeeded(LocalDateTime.parse(lastSavedAt))){
            val response = apiRequest { api.getAllProduct() }
            products.postValue(response)
        }
    }

    private fun isFetchNeeded(savedAt: LocalDateTime): Boolean {
        return ChronoUnit.HOURS.between(savedAt, LocalDateTime.now()) > Common.MINIMUM_INTERVAL
    }

    private fun saveProducts(products: List<Product>) {
        Coroutines.io {
            prefs.savelastSavedAt(LocalDateTime.now().toString())
            db.getProductDao().saveAllProduct(products)
        }
    }
}