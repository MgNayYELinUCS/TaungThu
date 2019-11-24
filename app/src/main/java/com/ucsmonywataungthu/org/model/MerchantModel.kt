package com.ucsmonywataungthu.org.model

data class MerchantModel(
    val city_id: Int,
    val created_at: Any,
    val deleted_at: Any,
    val id: Int,
    val lat: Int,
    val long: Int,
    val merchant_address: String,
    val merchant_name: String,
    val merchant_type: MerchantType,
    val merchant_type_id: String,
    val phone_number: String,
    val status: Int,
    val updated_at: Any,
    val user_id: Int
)