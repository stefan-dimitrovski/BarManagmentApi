package com.sorsix.barmanagmentapi.service

import com.sorsix.barmanagmentapi.config.PasswordEncoderConfig
import com.sorsix.barmanagmentapi.domain.User
import com.sorsix.barmanagmentapi.dto.RegisterDTO
import com.sorsix.barmanagmentapi.repository.AuthRepository
import org.slf4j.LoggerFactory
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val authRepository: AuthRepository,
    private val passwordEncoder: PasswordEncoderConfig
) : UserDetailsService {
    private val logger = LoggerFactory.getLogger(AuthService::class.java)

    override fun loadUserByUsername(username: String): User? =
        authRepository.findByEmail(username)

    fun registerUser(userDto: RegisterDTO): User =
        authRepository.save(
            User(
                email = userDto.email,
                password = passwordEncoder.passwordEncoder().encode(userDto.password),
                name = userDto.name,
                phoneNumber = userDto.phoneNumber,
            )
        )
}