package com.jinhanexample.designPattern.adapter.kotlin.operation

class AddOperationAdapter(private var operationAdaptee: OperationAdaptee) :
    AbstractOperationTarget() {

    override fun operate(firstNumber: Int, secondNumber: Int): Int {
        return operationAdaptee.calculate(OperationAdaptee.Add_Operation, firstNumber, secondNumber)
    }

}