package com.sorsix.barmanagmentapi.api.response

import com.sorsix.barmanagmentapi.domain.DrinkInOrder

sealed interface DrinkInOrderResponse

data class DrinkInOrderOk (val drinkInOrder: DrinkInOrder) : DrinkInOrderResponse
data class DrinkInOrderError (val message: String) : DrinkInOrderResponse