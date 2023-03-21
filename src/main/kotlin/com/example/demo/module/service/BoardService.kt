package com.example.demo.module.service

import com.example.demo.module.dto.*
import com.example.demo.module.entity.Board
import com.example.demo.module.entity.Comment
import com.example.demo.module.repository.BoardRepository
import com.example.demo.module.repository.CommentRepository
import com.example.demo.module.repository.UserRepository
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.stereotype.Service
import java.util.*
import javax.transaction.Transactional

@Service
class BoardService(
    private val boardRepository: BoardRepository,
    private val userRepository: UserRepository,
    private val commentRepository: CommentRepository
) {

    fun createBoard(boardDto: BoardDto): Board {
        val userId = userRepository.getById(boardDto.userId)
        boardDto.apply {
            return boardRepository.save(
                Board(
                    title,
                    content,
                    createdAt = Date(),
                    updatedAt = Date(),
                    userId,
                    listOf()
                )
            )
        }
    }

    @Transactional
    fun getBoardById(boardId: Long): BoardResponse {
        return BoardResponse.boardToBoardResponse(boardRepository.getById(boardId))
    }

    fun deleteById(boardId: Long) {
        return boardRepository.deleteById(boardId)
    }

    fun modifyById(boardId: Long, board: BoardModifyDto): Boolean {
        boardRepository.getById(boardId).apply {
            return if (user.userId == board.userId) {
                title = board.title
                content = board.content
                updatedAt = Date()
                boardRepository.save(this)
                true
            } else {
                false
            }
        }
    }

    fun postComment(boardId: Long, comment: CommentDto): Boolean {
        val user = userRepository.getById(comment.userId)
        val board = boardRepository.getById(boardId)
        comment.apply {
            commentRepository.save(Comment(id, user, board, content))
        }
        return true
    }

    fun modifyComment(commentId: Long, contentInput: CommentModifyDto): Boolean {
        val comment = commentRepository.getById(commentId)
        comment.apply {
            content = contentInput.content
        }
        commentRepository.save(comment)
        return true
    }

    fun deleteComment(commentId: Long): Boolean {
        try {
            commentRepository.deleteById(commentId)
        } catch (e: EmptyResultDataAccessException) {
            return false
        }
        return true
    }
}