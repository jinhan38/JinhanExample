package com.jinhanexample.designPattern.composite.java;

public class Calculator {

    private AbstractExpression expression;

    public void setExpression(AbstractExpression expression) {
        this.expression = expression;
    }
    public Calculator() {
        super();
    }

    public int calculate() {
        return expression.operate();
    }

}
