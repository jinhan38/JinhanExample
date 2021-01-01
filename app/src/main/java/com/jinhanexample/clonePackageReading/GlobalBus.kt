package com.jinhanexample.clonePackageReading

import com.squareup.otto.Bus

object GlobalBus {
       private var bus: Bus? = null

    fun getBus(): Bus {
        if (bus == null) {
               bus = Bus()
        }
        return bus!!
    }
}