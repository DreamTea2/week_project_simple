package com.zsd.codelab.list.service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.localbroadcastmanager.content.LocalBroadcastManager

/**
 * Create By shaodong on 2021/10/8 11:34
 * 广播工具类
 */
object LocalBroadcastUtils {

    /**
     * 注册广播
     * @param context 上下文
     * @param broadcastReceiver 广播接收器
     * @param intentFilter 广播过滤器
     */
    fun register (context: Context, broadcastReceiver: BroadcastReceiver,intentFilter: IntentFilter) {
        val localManager = LocalBroadcastManager.getInstance(context)
        localManager.registerReceiver(broadcastReceiver,intentFilter)
    }

    /**
     * 发送广播
     * @param context 上下文
     * @param intent  内容以及标识
     */
    fun sendBroadCast (context: Context,intent: Intent) {
        val localManager = LocalBroadcastManager.getInstance(context)
        localManager.sendBroadcast(intent)
    }

    /**
     * 注销广播
     * @param context 上下文
     * @param broadcastReceiver 广播接收器
     */
    fun unRegister (context: Context, broadcastReceiver: BroadcastReceiver) {
        val localManager = LocalBroadcastManager.getInstance(context)
        localManager.unregisterReceiver(broadcastReceiver)
    }
}