package com.smv.hieunt.navigationdrawertest1.data.sources.remote.response



data class InsertResponse(
    val data: String?,
    val error: String?,
    val errorCode: Int,
    val message: String,
    val result: Int
)