package com.jinhanexample.designPattern.adapter.java.operation;

public class MultiplyOperation extends AbstractOperationTarget {
    @Override
    public int operate(int firstNumber, int secondNumber) {
        return firstNumber * secondNumber;
    }
}
