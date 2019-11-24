package com.ucsmonywataungthu.org.model

import com.google.gson.annotations.SerializedName

data class ServerResult (
    @SerializedName("crop_subcategory_get_all")
    val result:List<CropSubcategory>?=null
)

data class CropResult(
    @SerializedName("crop_category_get_one")
    val cropresult:List<CropSubcategory>?=null
)
data class CropDetailResult(
    @SerializedName("crop_subcategory_get_one")
    val cropdetailresult:List<CropDetailsGetAll>?=null
)