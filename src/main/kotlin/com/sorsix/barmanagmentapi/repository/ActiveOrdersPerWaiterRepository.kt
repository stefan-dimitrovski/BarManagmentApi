package com.sorsix.barmanagmentapi.repository

import com.sorsix.barmanagmentapi.domain.views.ActiveOrdersPerWaiter
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ActiveOrdersPerWaiterRepository : JpaRepository<ActiveOrdersPerWaiter, Long> {
    
    fun findByTableIdAndWaiterId(tableId: Long, waiterId: Long): List<ActiveOrdersPerWaiter>?
}