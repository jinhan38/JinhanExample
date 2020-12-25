package com.jinhanexample.designPattern.adapter.java.operation;

public class AddOperation extends AbstractOperationTarget {

    @Override
    public int operate(int firstNumber, int secondNumber) {
        return firstNumber + secondNumber;
    }
}
