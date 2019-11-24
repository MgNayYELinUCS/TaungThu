package com.ucsmonywataungthu.org.model

data class QuestionGetAll(
    val created_at: String?,
    val deleted_at: Any,
    val id: Int?,
    val question_description: String?,
    val question_photo: String?,
    val question_title: String?,
    val updated_at: String?,
    val user_id: Int?,
    val users: Users?
)