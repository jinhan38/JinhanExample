package com.jinhanexample.designPattern.adapter.java.operation;

public class OperationAdaptee {
    public static final int ADD_OPERATION = 1;
    public static final int SUBTRACT_OPERATION = 2;
    public static final int MULTIPLY_OPERATION = 3;
    public static final int DIVIDE_OPERATION = 4;

    public OperationAdaptee() {
        super();
    }

    public int calculate(int operatorType, int firstNumber, int secondNumber) {
        switch (operatorType) {
            case ADD_OPERATION:
                return firstNumber + secondNumber;
            case SUBTRACT_OPERATION:
                return firstNumber - secondNumber;
            case MULTIPLY_OPERATION:
                return firstNumber * secondNumber;
            case DIVIDE_OPERATION:
                return firstNumber / secondNumber;
        }
        return 0;
    }
}
