package com.smv.hieunt.navigationdrawertest1.data.sources.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.smv.hieunt.navigationdrawertest1.data.model.Customer
import com.smv.hieunt.navigationdrawertest1.data.model.Product

@Database(entities = [Customer::class, Product::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getCustomerDao() : CustomerDao
    abstract fun getProductDao() : ProductDao

    companion object{
        @Volatile
        private var instance: AppDatabase? = null
        private var LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "MyDatabase.db"
            ).build()
    }


}