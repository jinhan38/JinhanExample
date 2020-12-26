package com.jinhanexample.designPattern.composite.java;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;

import androidx.annotation.Nullable;

import com.jinhanexample.R;
import com.jinhanexample.others.CommonActivity;

public class CompositeClientJava extends CommonActivity {

    private static final String TAG = "CompositeClientJava";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            setContentView(R.layout.composite_client_java);

        Calculator calculator = new Calculator();
        int firstNumber = 100;
        int secondNumber = 20;


        //NumberExpression is Class that save a value and is used to parameter
        NumberExpression firstNumberEquation = new NumberExpression(firstNumber);
        NumberExpression secondNumberEquation = new NumberExpression(secondNumber);

        //The variable type of setExpression method is AbstractExpression class.
        //Because NumberExpression extends AbstractExpression class, it can be used as a parameter of setExpression function
        calculator.setExpression(firstNumberEquation);
        Log.d(TAG, "onCreate: firstNumber : " + calculator.calculate());

        calculator.setExpression(secondNumberEquation);
        Log.d(TAG, "onCreate: secondNumberEquation : " + calculator.calculate());


        //AddOperationExpression class extends AbstractOperationExpression class.
        //Upcast AddOperationExpression to AbstractOperationExpression

        AbstractOperationExpression addOperationExpression = new AddOperationExpression();
        //parameter type of addOperandExpression function is AbstractExpression class.
        //If call addOperandExpression function, the value is added to operandList in AbstractOperationExpression class
        addOperationExpression.addOperandExpression(firstNumberEquation);
        addOperationExpression.addOperandExpression(secondNumberEquation);

        //Now, you can get calculated results using calculator class
        calculator.setExpression(addOperationExpression);
        Log.d(TAG, "onCreate: add Result : " + calculator.calculate());

        AbstractOperationExpression subtractOperationExpression = new SubtractOperationExpression();
        subtractOperationExpression.addOperandExpression(firstNumberEquation);
        subtractOperationExpression.addOperandExpression(secondNumberEquation);
        calculator.setExpression(subtractOperationExpression);
        Log.d(TAG, "onCreate: subtract result : " + calculator.calculate());


        AbstractOperationExpression multiplyOperationExpression = new MultiplyOperationExpression();
        multiplyOperationExpression.addOperandExpression(firstNumberEquation);
        multiplyOperationExpression.addOperandExpression(secondNumberEquation);
        calculator.setExpression(multiplyOperationExpression);
        Log.d(TAG, "onCreate: multiply result : " + calculator.calculate());

        AbstractOperationExpression divideOperationExpression = new DivideOperationExpression();
        divideOperationExpression.addOperandExpression(firstNumberEquation);
        divideOperationExpression.addOperandExpression(secondNumberEquation);
        calculator.setExpression(divideOperationExpression);
        Log.d(TAG, "onCreate: divide result : " + calculator.calculate());

    }

}
