package com.example.demo.module.controller

import com.example.demo.module.dto.*
import com.example.demo.module.entity.Board
import com.example.demo.module.service.BoardService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/board")
class BoardController(private val boardService: BoardService) {
    @PostMapping("") // 게시글 작성
    fun createBoard(@RequestBody request: BoardDto): Board {
        return boardService.createBoard(request)
    }

    @GetMapping("/{boardId}") // 게시글 불러오기
    fun getBoardById(@PathVariable boardId: Long): BoardResponse {
        return boardService.getBoardById(boardId)
    }

    @DeleteMapping("/{boardId}") // 게시글 삭제
    fun deleteBoardById(@PathVariable boardId: Long) {
        return boardService.deleteById(boardId)
    }

    @PatchMapping("/{boardId}") // 게시글 수정
    fun modifyBoardById(@PathVariable boardId: Long, @RequestBody request: BoardModifyDto): Boolean {
        return boardService.modifyById(boardId, request)
    }

    //댓글
    @PostMapping("/{boardId}/comment") //댓글 작성
    fun postComment(@PathVariable boardId: Long, @RequestBody request: CommentDto): Boolean {
        return boardService.postComment(boardId, request)
    }

    @PatchMapping("/comment/{commentId}") // 댓글 수정
    fun modifyComment(@PathVariable commentId: Long, @RequestBody request: CommentModifyDto): Boolean {
        return boardService.modifyComment(commentId, request)
    }

    @DeleteMapping("/comment/{commentId}") // 댓글 삭제
    fun deleteComment(@PathVariable commentId: Long): Boolean {
        return boardService.deleteComment(commentId)
    }
}