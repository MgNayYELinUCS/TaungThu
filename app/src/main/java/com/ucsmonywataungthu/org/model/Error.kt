package com.ucsmonywataungthu.org.model

data class Error(
    val confirm_password: List<String>,
    val email: List<String>,
    val name: List<String>,
    val password: List<String>
)