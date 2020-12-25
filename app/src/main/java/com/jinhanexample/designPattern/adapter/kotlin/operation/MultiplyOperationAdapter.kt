package com.jinhanexample.designPattern.adapter.kotlin.operation

class MultiplyOperationAdapter(private var operationAdaptee: OperationAdaptee) :
    AbstractOperationTarget() {

    override fun operate(firstNumber: Int, secondNumber: Int): Int {
        return operationAdaptee.calculate(
            OperationAdaptee.MULTIPLY_OPERATION,
            firstNumber,
            secondNumber
        )
    }

}