package com.jinhanexample.designPattern.adapter.java.operation;

public class DivideOperationAdapter extends AbstractOperationTarget {
    protected OperationAdaptee operationAdaptee;

    public DivideOperationAdapter(OperationAdaptee operationAdaptee) {
        this.operationAdaptee = operationAdaptee;
    }

    @Override
    public int operate(int firstNumber, int secondNumber) {
        return operationAdaptee.calculate(OperationAdaptee.DIVIDE_OPERATION, firstNumber, secondNumber);
    }
}
