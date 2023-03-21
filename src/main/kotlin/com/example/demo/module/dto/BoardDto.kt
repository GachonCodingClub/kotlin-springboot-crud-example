package com.example.demo.module.dto

data class BoardDto(
    val id: Long = 0,
    val title: String,
    val content: String,
    val userId: String
)