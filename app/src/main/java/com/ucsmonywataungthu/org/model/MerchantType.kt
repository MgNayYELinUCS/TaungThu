package com.ucsmonywataungthu.org.model

data class MerchantType(

    val id: Int,
    val merchant_type_name: String
){
    override fun toString(): String {
        return merchant_type_name
    }
}