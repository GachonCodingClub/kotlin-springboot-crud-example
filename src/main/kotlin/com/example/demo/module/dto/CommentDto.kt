package com.example.demo.module.dto

import com.example.demo.module.entity.Comment

data class CommentDto(
    var id: Long = 0,
    var userId: String,
    var content: String
) {
    companion object {
        fun commentToDto(comment: Comment): CommentDto {
            with(comment) {
                return CommentDto(
                    id,
                    user.userId,
                    content
                )
            }
        }
    }
}