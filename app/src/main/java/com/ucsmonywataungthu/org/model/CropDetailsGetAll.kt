package com.ucsmonywataungthu.org.model

data class CropDetailsGetAll(
    val created_at: Any,
    val crop_description: String,
    val crop_fertilizer: String,
    val crop_name: String,
    val crop_store_method: String,
    val crop_subcategory: CropSubcategory,
    val crop_subcategory_id: Int,
    val deleted_at: Any,
    val id: Int,
    val updated_at: Any
)