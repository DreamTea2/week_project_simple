package com.zsd.codelab.list.service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.IntentFilter

/**
 * Create By shaodong on 2021/10/8 11:43
 */
object NetworkBroadcastReceiverHelper {

    private lateinit var mReceiver: BroadcastReceiver

    fun register(context: Context, listener: OnObservableNetListener) {
        mReceiver = ObservableBroadCast(listener)
        val action = IntentFilter()
        action.addAction(ObservableBroadCast.NET_CONNECT)
        action.addAction(ObservableBroadCast.NET_DIS_CONNECT)
        action.addAction(ObservableBroadCast.NET_CONNECT_WIFI)
        action.addAction(ObservableBroadCast.NET_CONNECT_DATA)
        LocalBroadcastUtils.register(context, mReceiver, action)
    }

    fun unRegister(context: Context) {
        mReceiver.let {
            LocalBroadcastUtils.unRegister(context, it)
        }
    }
}