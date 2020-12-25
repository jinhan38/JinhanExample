package com.jinhanexample.designPattern.adapter.java.operation;

public class AddOperationAdapter extends AbstractOperationTarget {
    protected OperationAdaptee operationAdaptee;

    public AddOperationAdapter(OperationAdaptee operationAdaptee) {
        this.operationAdaptee = operationAdaptee;
    }

    @Override
    public int operate(int firstNumber, int secondNumber) {
        return operationAdaptee.calculate(OperationAdaptee.ADD_OPERATION, firstNumber, secondNumber);
    }
}
