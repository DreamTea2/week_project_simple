package com.zsd.codelab.list.service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

/**
 * Create By shaodong on 2021/10/8 13:46
 */
class ObservableBroadCast(private val listener: OnObservableNetListener?) : BroadcastReceiver() {
    private  val TAG = "ObservableBroadCast"
    companion object {
        const val NET_CONNECT = "ACTION_NET_CONNECT" // 网络已经连接
        const val NET_DIS_CONNECT = "ACTION_NET_DIS_CONNECT" // 网络没有连接
        const val NET_CONNECT_WIFI = "ACTION_NET_CONNECT_WIFI" // 网络连接为wifi
        const val NET_CONNECT_DATA = "ACTION_NET_CONNECT_DATA" // 网络连接为数据网络
    }

    override fun onReceive(context: Context?, intent: Intent?) {

        Log.d(TAG, "onReceive: status -->${intent?.action} ")

        when (intent?.action) {
            NET_CONNECT -> {
                listener?.onActionConnect()
            }
            NET_DIS_CONNECT -> {
                listener?.onActionUnConnect()
            }
            NET_CONNECT_WIFI -> {
                listener?.onActionConnectWifi()
            }
            NET_CONNECT_DATA -> {
                listener?.onActionConnectData()
            }
        }
    }
}