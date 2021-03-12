package com.jinhanexample.eventBus

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jinhanexample.R
import com.jinhanexample.eventBus.event.EventsNickname
import com.jinhanexample.eventBus.event.GlobalBus
import com.squareup.otto.Subscribe
import kotlinx.android.synthetic.main.activity_event_bus_sample_kotlin.*

class EventBusSampleActivityKotlin : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_bus_sample_kotlin)
        GlobalBus.bus!!.register(this)
        val eventBusNickname = EventsNickname("jinhan")

        btnEventPost.setOnClickListener {

            GlobalBus.bus!!.post(eventBusNickname)

        }

    }

    @Subscribe
    fun getBusData(eventBusNickname: EventsNickname) {
        tvEventNickName.text = eventBusNickname.nickname
    }

    override fun onDestroy() {
        super.onDestroy()
        GlobalBus.bus!!.unregister(this)
    }

}