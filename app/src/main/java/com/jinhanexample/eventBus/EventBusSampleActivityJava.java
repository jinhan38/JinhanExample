package com.jinhanexample.eventBus;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.jinhanexample.R;
import com.jinhanexample.eventBus.event.EventsNickname;
import com.jinhanexample.eventBus.event.GlobalBus;
import com.squareup.otto.Subscribe;

public class EventBusSampleActivityJava extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus_sample_kotlin);

        GlobalBus.INSTANCE.getBus().register(this);

        EventsNickname eventsNickname = new EventsNickname("Jinhan");

        ((Button) findViewById(R.id.btnEventPost)).setOnClickListener(view -> {

            GlobalBus.INSTANCE.getBus().post(eventsNickname);

        });

    }

    @Subscribe
    public void getBusData(EventsNickname eventsNickname) {

        ((TextView) findViewById(R.id.tvEventNickName)).setText(eventsNickname.getNickname());

    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
        GlobalBus.INSTANCE.getBus().unregister(this);

    }

}
