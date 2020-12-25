package com.jinhanexample.designPattern.adapter.kotlin.operation

class DivideOperationAdapter(private var operationAdaptee: OperationAdaptee) :
    AbstractOperationTarget() {

    override fun operate(firstNumber: Int, secondNumber: Int): Int {
        return operationAdaptee.calculate(
            OperationAdaptee.DIVIDE_OPERATION,
            firstNumber,
            secondNumber
        )
    }

}