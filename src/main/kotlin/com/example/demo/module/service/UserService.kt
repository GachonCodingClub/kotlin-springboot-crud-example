package com.example.demo.module.service

import com.example.demo.module.dto.UserDto
import com.example.demo.module.entity.User
import com.example.demo.module.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository) {

    fun applyUser(userDto: UserDto): User {
        userDto.apply {
            return userRepository.save(User(userId, userName, userPassword))
        }
    }

    fun deleteUser(userId: String) {
        return userRepository.deleteById(userId)
    }
}