package com.sorsix.barmanagmentapi.domain

import javax.persistence.*
import javax.persistence.Table

@Entity
@Table(name = "locales")
data class Locale(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    val address: String,
    val name: String,
    val lat: Double,
    val lng: Double,
    @OneToMany
    val waiters: List<User>
)
