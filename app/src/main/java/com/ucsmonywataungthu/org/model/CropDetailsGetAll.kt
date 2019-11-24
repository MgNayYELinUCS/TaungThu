package com.ucsmonywataungthu.org.model

data class CropDetailsGetAll(
    val crop_description: String,
    val crop_fertilizer: String,
    val crop_name: String,
    val crop_store_method: String,
    val crop_subcategory_id: Int,
    val id: Int
)
{
    override fun toString(): String {
        return crop_name
    }
}