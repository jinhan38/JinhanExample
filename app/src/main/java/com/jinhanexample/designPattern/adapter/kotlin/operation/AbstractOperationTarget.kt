package com.jinhanexample.designPattern.adapter.kotlin.operation

abstract class AbstractOperationTarget {
    abstract fun operate(firstNumber: Int, secondNumber: Int) : Int
}