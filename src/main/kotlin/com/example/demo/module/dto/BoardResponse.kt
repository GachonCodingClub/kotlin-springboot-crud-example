package com.example.demo.module.dto

import com.example.demo.module.entity.Board

data class BoardResponse(
    val title: String,
    val writer: String,
    val content: String,
    val commentList: List<CommentDto>
) {
    companion object {
        fun boardToBoardResponse(board: Board): BoardResponse {
            with(board) {
                return BoardResponse(
                    title,
                    user.userId,
                    content,
                    commentList.map {
                        CommentDto.commentToDto(it)
                    }

                )
            }
        }
    }
}