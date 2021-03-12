package com.jinhanexample.eventBus.event

import com.squareup.otto.Bus

object GlobalBus {
    private var sBus: Bus? = null
    val bus: Bus?
        get() {
            if (sBus ==null) sBus = Bus()
            return sBus
        }
}