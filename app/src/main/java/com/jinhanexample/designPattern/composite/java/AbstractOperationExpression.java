package com.jinhanexample.designPattern.composite.java;

import java.util.ArrayList;

abstract public class AbstractOperationExpression extends AbstractExpression {
    protected ArrayList<AbstractExpression> operandList = new ArrayList<>();

    public final void addOperandExpression(AbstractExpression operandExpression){
        operandList.add(operandExpression);
    }
    
}
