package com.ucsmonywataungthu.org.model

data class DailyPriceModel(val id:Int,
                           val crop_type:String,
                           val crop_price_model:List<CropPriceModel>)