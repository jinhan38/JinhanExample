package com.jinhanexample.designPattern.adapter.kotlin.operation

class SubtractOperationAdapter(private var operationAdaptee: OperationAdaptee) :
    AbstractOperationTarget() {

    override fun operate(firstNumber: Int, secondNumber: Int): Int {
        return operationAdaptee.calculate(
            OperationAdaptee.SUBTRACT_OPERATION,
            firstNumber,
            secondNumber
        )
    }

}