package com.sorsix.barmanagmentapi.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "drinks_in_order")
data class DrinkInOrder(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,
    @ManyToOne
    val order: Order,
    @ManyToOne
    val drink: Drink,
    val quantity: Int = 0,
)
