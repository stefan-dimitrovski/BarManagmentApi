package com.sorsix.barmanagmentapi.config

import com.sorsix.barmanagmentapi.domain.User
import io.jsonwebtoken.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.util.*


@Service
class JwtUtils {

    private val logger: Logger = LoggerFactory.getLogger(JwtUtils::class.java)

    fun generateJwtToken(authentication: Authentication): String {
        val userPrincipal: User = authentication.principal as User
        return Jwts.builder()
            .setSubject(userPrincipal.username)
            .setIssuedAt(Date())
            .setExpiration(Date(Date().time + JwtConstants.EXPIRATION_TIME))
            .signWith(SignatureAlgorithm.HS512, JwtConstants.SECRET)
            .compact()
    }

    fun generateUsernameToken(token: String?): String {
        return Jwts.parser().setSigningKey(JwtConstants.SECRET).parseClaimsJws(token).body.subject
    }

    fun validateJwtToken(authToken: String?): Boolean {
        try {
            Jwts.parser().setSigningKey(JwtConstants.SECRET).parseClaimsJws(authToken)
            return true
        } catch (e: Exception){
            logger.error("JWT token is invalid")
        }
        return false
    }


}