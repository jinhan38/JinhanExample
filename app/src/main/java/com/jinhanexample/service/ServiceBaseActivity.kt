package com.jinhanexample.service

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.jinhanexample.R
import com.jinhanexample.service.musicPlayer.MusicPlayerService
import kotlinx.android.synthetic.main.activity_service_base.*

class ServiceBaseActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var musicPlayerService: MusicPlayerService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service_base)
        btnMusicPlayerService.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view!!.id) {
            R.id.btnMusicPlayerService -> {
                Log.d(TAG, "onClick: ServiceBaseActivity")
                val playerIntent = Intent(this, MusicPlayerService::class.java)
                startService(playerIntent)
                bindService(playerIntent, serviceConnection, Context.BIND_AUTO_CREATE)
            }
        }
    }


    private val serviceConnection: ServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(p0: ComponentName?, service: IBinder?) {
            val binder: MusicPlayerService.LocalBinder = service as MusicPlayerService.LocalBinder
            musicPlayerService = binder.service
        }

        override fun onServiceDisconnected(p0: ComponentName?) {
        }

    }


    companion object {
        private const val TAG = "ServiceBaseActivity"
    }
}