package com.jinhanexample.designPattern.composite.java;

public class NumberExpression extends AbstractExpression {

    private int value;

    public NumberExpression(int value) {
        this.value = value;
    }

    @Override
    public int operate() {
        return value;
    }
}
