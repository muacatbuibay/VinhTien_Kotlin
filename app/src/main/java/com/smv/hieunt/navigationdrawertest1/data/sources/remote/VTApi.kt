package com.smv.hieunt.navigationdrawertest1.data.sources.remote

import com.smv.hieunt.navigationdrawertest1.data.model.Customer
import com.smv.hieunt.navigationdrawertest1.data.model.Product
import com.smv.hieunt.navigationdrawertest1.data.sources.remote.response.InsertResponse
import com.smv.hieunt.navigationdrawertest1.data.sources.remote.response.ProductResponse
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface VTApi {
    @GET("customerService/getById")
    suspend fun getById(@Query("id") id: String): Response<Customer>

    @POST("customerService/insert")
    suspend fun signUp(@Body customer: Customer): Response<InsertResponse>

    @POST("customerService/update")
    suspend fun updateCustomer(@Body customer: Customer): Response<InsertResponse>

    @GET("goodsService/getAll")
    suspend fun getAllProduct(): Response<List<Product>>

    @GET("goodService/getById")
    suspend fun getProductById(@Query("id") id: String): Response<Product>

    companion object{
        operator fun invoke(
            networConnectionInterrceptor: NetworkConnectionInterceptor
        ) : VTApi{

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(networConnectionInterrceptor)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("http://171.244.10.92:8786/BMSService/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(VTApi::class.java)
        }
    }
}