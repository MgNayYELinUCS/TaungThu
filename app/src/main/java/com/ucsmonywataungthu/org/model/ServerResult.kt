package com.ucsmonywataungthu.org.model

import com.google.gson.annotations.SerializedName

class ServerResult {
    @SerializedName("crop_subcategory_get_all")
    val result:List<CropSubcategory>?=null
}