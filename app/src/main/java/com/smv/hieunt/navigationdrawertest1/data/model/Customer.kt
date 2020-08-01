package com.smv.hieunt.navigationdrawertest1.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.smv.hieunt.navigationdrawertest1.utils.Common


@Entity
data class Customer (
    var defaultSortField: String? = null,
    var action: String? = null,
    var id: String? = null,
    var code: String? = null,
    var name: String? = null,
    var customerType: String? = null,
    var address: String? = null,
    var phoneNumber: String? = null,
    var email: String? = null,
    var fax: String? = null,
    var description: String? = null,
    var agentName: String? = null,
    var status: String? = null,
    var createdBy: String? = null,
    var createdDate: String? = null,
    var lastUpdatedBy: String? = null,
    var lastUpdatedDate: String? = null,
    var province: String? = null,
    var district: String? = null,
    var commune: String? = null,
    var policyPrice: String? = null,
    var policyPriceName: String? = null,
    var customerGroupId: String? = null,
    var locationMap: String? = null,
    var departmentId: String? = null,
    var policyPriceType: String? = null,
    var supId: String? = null,
    var isMapDisplay: String? = null,
    var fwmodelId: String? = null

){
    @PrimaryKey(autoGenerate = false)
    var customerid: Int = Common.CURRENT_USER_ID
}