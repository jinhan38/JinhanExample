package com.jinhanexample.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.jinhanexample.R;

public class BroadCastActivityJava extends AppCompatActivity {

    private final static String BROAD_CAST_MESSAGE = "broadCastTest";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broad_cast);

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BROAD_CAST_MESSAGE);
        registerReceiver(broadCastController, intentFilter);


        findViewById(R.id.btnBroadCastTest).setOnClickListener(view -> {
            sendBroadcast(new Intent(BROAD_CAST_MESSAGE));
        });

    }


    private BroadcastReceiver broadCastController = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            if (intent.getAction() == BROAD_CAST_MESSAGE) {
                Toast.makeText(context, "broadCast test message", Toast.LENGTH_SHORT).show();
            }
        }
    };
    
}
