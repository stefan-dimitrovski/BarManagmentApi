package com.sorsix.barmanagmentapi.service

import com.sorsix.barmanagmentapi.domain.Order
import com.sorsix.barmanagmentapi.repository.OrderRepository
import com.sorsix.barmanagmentapi.repository.TableRepository
import com.sorsix.barmanagmentapi.repository.AuthRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import javax.transaction.Transactional

@Service
class OrderService(
    private val orderRepository: OrderRepository,
    private val tableRepository: TableRepository,
    private val authRepository: AuthRepository
) {

    fun getAllOrders(): List<Order> = orderRepository.findAll()

    fun findOrderById(id: Long): Order? =
        orderRepository.findById(id).orElseGet(null)

    @Transactional
    fun openOrder(tableId: Long, waiterId: Long): Order {
        val table = tableRepository.findById(tableId).get()
        table.isOpen = false
        val waiter = authRepository.findById(waiterId).get()
        val order = Order(table = table, waiter = waiter)
        return orderRepository.save(order)
    }

    @Transactional
    fun closeOrder(orderId: Long): Order {
        val order = findOrderById(orderId)!!
        order.table.isOpen = true
        order.closedAt = LocalDateTime.now()
        return order
    }
}
