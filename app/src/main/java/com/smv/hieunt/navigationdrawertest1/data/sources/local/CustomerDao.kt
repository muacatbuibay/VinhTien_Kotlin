package com.smv.hieunt.navigationdrawertest1.data.sources.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.smv.hieunt.navigationdrawertest1.data.model.Customer
import com.smv.hieunt.navigationdrawertest1.utils.Common
import com.smv.hieunt.navigationdrawertest1.utils.Common.CURRENT_USER_ID

@Dao
interface CustomerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(customer: Customer): Long

    @Query("select * from customer where customerid = $CURRENT_USER_ID")
    fun getCustomer(): LiveData<Customer>

    @Query("delete from customer where customerid = $CURRENT_USER_ID")
    suspend fun deleteCustomer()

}