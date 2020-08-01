package com.smv.hieunt.navigationdrawertest1.data.repositories

import androidx.room.PrimaryKey
import com.smv.hieunt.navigationdrawertest1.data.model.Customer
import com.smv.hieunt.navigationdrawertest1.data.sources.local.AppDatabase
import com.smv.hieunt.navigationdrawertest1.data.sources.remote.SafeApiRequest
import com.smv.hieunt.navigationdrawertest1.data.sources.remote.VTApi
import com.smv.hieunt.navigationdrawertest1.data.sources.remote.response.InsertResponse

class CustomerRepository(
    private val api: VTApi,
    private val db: AppDatabase

) : SafeApiRequest() {

    suspend fun getCustomerById(id: String) : Customer {
        return apiRequest {
            api.getById(id)
        }
    }

    suspend fun insertCustomer(customer: Customer) : InsertResponse{
        return apiRequest {
            api.signUp(customer)
        }
    }

    suspend fun updateCustomer(customer: Customer) : InsertResponse{
        return apiRequest {
            api.updateCustomer(customer)
        }
    }

    suspend fun saveCustomer(customer: Customer) = db.getCustomerDao().upsert(customer)

    fun getCustomer() = db.getCustomerDao().getCustomer()

    suspend fun deleteCustomer() = db.getCustomerDao().deleteCustomer()

}