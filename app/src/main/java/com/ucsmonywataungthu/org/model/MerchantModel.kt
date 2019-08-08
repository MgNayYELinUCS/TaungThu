package com.ucsmonywataungthu.org.model

data class MerchantModel (val id:Int,
                          val merchant_image:Int,
                          val merchant_name:String,
                          val merchant_type:MerchantTypeModel,
                          val merchant_address:String,
                          val phone_number:String,
                          val email:String)