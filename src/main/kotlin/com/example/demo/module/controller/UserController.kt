package com.example.demo.module.controller

import com.example.demo.module.dto.UserDto
import com.example.demo.module.entity.User
import com.example.demo.module.service.UserService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class UserController(private val userService: UserService) {

    @PostMapping("/apply")
    fun applyUser(@RequestBody request: UserDto): User {
        return userService.applyUser(request)
    }

    @DeleteMapping("/delete/{userId}")
    fun deleteUser(@PathVariable userId: String) {
        return userService.deleteUser(userId)
    }
}