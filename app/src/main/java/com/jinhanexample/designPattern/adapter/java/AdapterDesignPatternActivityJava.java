package com.jinhanexample.designPattern.adapter.java;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.jinhanexample.R;
import com.jinhanexample.designPattern.adapter.java.operation.AbstractOperationTarget;
import com.jinhanexample.designPattern.adapter.java.operation.AddOperation;
import com.jinhanexample.designPattern.adapter.java.operation.AddOperationAdapter;
import com.jinhanexample.designPattern.adapter.java.operation.DivideOperationAdapter;
import com.jinhanexample.designPattern.adapter.java.operation.MultiplyOperation;
import com.jinhanexample.designPattern.adapter.java.operation.OperationAdaptee;
import com.jinhanexample.designPattern.adapter.java.operation.SubtractOperation;

public class AdapterDesignPatternActivityJava extends AppCompatActivity {

    private static final String TAG = "AdapterDesignClientActi";
    private int firstNumber = 100;
    private int secondNumber = 20;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adapter_design_pattern_java);

        AbstractOperationTarget operationTarget;

        //더하기
        //추상메소드인 operationTarget을 AddOperation으로 업캐스팅
        //업캐스팅한 operationTarget의 operate함수를 호출하면
        //AddOperation에서 설정해놓은 return firstNumber + secondNumber;가 실행된다.
        operationTarget = new AddOperation();
        int answer = operationTarget.operate(firstNumber, secondNumber);
        Log.d(TAG, "onCreate: add : " + answer);

        //빼기
        operationTarget = new SubtractOperation();
        answer = operationTarget.operate(firstNumber, secondNumber);
        Log.d(TAG, "onCreate: subtract : " + answer);

        //곱하기
        operationTarget = new MultiplyOperation();
        answer = operationTarget.operate(firstNumber, secondNumber);
        Log.d(TAG, "onCreate: multiply : " + answer);


        //앞선 더하기 빼기 곱하기는 operationTarget을 바로 업캐스팅하여 값을 구했다.
        //나누기는 adapter를 사용해 값을 구했다.
        //operationAdaptee 생성
        // DivideOperationAdapter를 생성하여 operationAdaptee 주입
        // DivideOperationAdapter의 operate함수에서 operationAdaptee의 calculate함수 호출
        //이때 calculate함수에 operationtype 값을 OperationAdaptee.DIVIDE_OPERATION로 입력
        //calculate함수에서 OperationAdaptee.DIVIDE type은 firstNumber / secondNumber 반환
        //이처럼 Adapter를 이용한다면 모든 계산 수식을 OperationAdaptee.calculate함수에서 컨트롤 가능하다
        //새로운 요구사항을 추가하고 싶을 때 Adapter 클래스를 추가로 생성하여 타입을 주입하고
        //필요한 파라메터들을 넘겨준다.

        //Adapter 패턴 나누기
        OperationAdaptee operationAdaptee = new OperationAdaptee();
        operationTarget = new DivideOperationAdapter(operationAdaptee);
        answer = operationTarget.operate(firstNumber, secondNumber);
        Log.d(TAG, "onCreate: adapter divide : " + answer);


        //Adapter 패턴 더하기
        operationTarget = new AddOperationAdapter(operationAdaptee);
        answer = operationTarget.operate(firstNumber, secondNumber);
        Log.d(TAG, "onCreate: adapter add : " + answer);

    }
}
