package com.zsd.codelab.list.service

import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.IBinder
import android.util.Log

/**
 * Create By shaodong on 2021/10/8 10:40
 */
class NetService : Service() {
    private val TAG = "MyService"

    private lateinit var  broadcastReceiver:MyNetWorkBroadCast
    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate: --> onCreate 开始拉起服务")
        val intentFilter= IntentFilter()
        broadcastReceiver = MyNetWorkBroadCast (applicationContext)
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(broadcastReceiver,intentFilter);
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "onStartCommand: --> onStartCommand")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent?): IBinder? {
        Log.d(TAG, "onBind: --> onBind")
        throw  UnsupportedOperationException("Not yet implemented");
    }

    override fun onDestroy() {
        unregisterReceiver(broadcastReceiver)
        super.onDestroy()
        Log.d(TAG, "onDestroy: --> onDestroy")
    }
}