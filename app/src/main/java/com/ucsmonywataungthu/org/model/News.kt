package com.ucsmonywataungthu.org.model

data class News(
    val created_at: Any,
    val deleted_at: Any,
    val id: Int,
    val news_description: String,
    val news_photo: String,
    val news_title: String,
    val updated_at: Any
)