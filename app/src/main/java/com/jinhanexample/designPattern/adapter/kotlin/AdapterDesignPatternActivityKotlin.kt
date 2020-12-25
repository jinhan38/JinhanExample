package com.jinhanexample.designPattern.adapter.kotlin

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.jinhanexample.R
import com.jinhanexample.designPattern.adapter.kotlin.operation.*

class AdapterDesignPatternActivityKotlin : AppCompatActivity() {

    companion object {
        private const val TAG = "AdapterDesignPatternAct"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adapter_design_pattern_kotlin)
        //activity_adapter_design_pattern_kotlin는 아무 코드없는 비어있는 layout

        var abstractOperationTarget: AbstractOperationTarget
        val operationAdaptee = OperationAdaptee()
        val firstNumber = 150
        val secondNumber = 60
        var answer = 0

        //더하기
        abstractOperationTarget = AddOperationAdapter(operationAdaptee)
        answer = abstractOperationTarget.operate(firstNumber, secondNumber)
        Log.d(TAG, "onCreate: 더하기 : $answer")

        //빼기
        abstractOperationTarget = SubtractOperationAdapter(operationAdaptee)
        answer = abstractOperationTarget.operate(firstNumber, secondNumber)
        Log.d(TAG, "onCreate: 빼기 : $answer")

        //곱하기
        abstractOperationTarget = MultiplyOperationAdapter(operationAdaptee)
        answer = abstractOperationTarget.operate(firstNumber, secondNumber)
        Log.d(TAG, "onCreate: 곱하기 : $answer")

        //나누기
        abstractOperationTarget = DivideOperationAdapter(operationAdaptee)
        answer = abstractOperationTarget.operate(firstNumber, secondNumber)
        Log.d(TAG, "onCreate: 나누기 : $answer")
    }

}