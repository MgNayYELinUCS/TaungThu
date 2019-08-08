package com.ucsmonywataungthu.org.model

data class AnswerGetOneQuestion(
    val answer_description: String,
    val created_at: Any,
    val deleted_at: Any,
    val id: Int,
    val question_id: Int,
    val updated_at: Any,
    val user_id: Int,
    val users: Users
)