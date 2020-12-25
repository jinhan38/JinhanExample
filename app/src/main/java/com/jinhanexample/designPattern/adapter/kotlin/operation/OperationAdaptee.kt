package com.jinhanexample.designPattern.adapter.kotlin.operation

class OperationAdaptee {

    companion object {
        const val Add_Operation = 1
        const val SUBTRACT_OPERATION = 2
        const val MULTIPLY_OPERATION = 3
        const val DIVIDE_OPERATION = 4
    }

    fun calculate(operationType: Int, firstNumber: Int, secondNumber: Int): Int {
        return when (operationType) {
            Add_Operation -> {
                firstNumber + secondNumber
            }
            SUBTRACT_OPERATION -> {
                firstNumber - secondNumber
            }
            MULTIPLY_OPERATION -> {
                firstNumber * secondNumber
            }
            DIVIDE_OPERATION -> {
                firstNumber / secondNumber
            }
            else -> 0
        }


    }
}