package com.zsd.codelab.list.service

import android.R
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.ConnectivityManager.CONNECTIVITY_ACTION
import android.net.NetworkInfo
import android.R.attr.action
import android.util.Log


/**
 * Create By shaodong on 2021/10/8 10:59
 * 网络广播
 */
class MyNetWorkBroadCast(var context: Context) : BroadcastReceiver() {
    private val TAG = "MyNetWorkBroadCast"

    companion object {
        const val NET_CONNECT = "ACTION_NET_CONNECT" // 网络已经连接
        const val NET_DIS_CONNECT = "ACTION_NET_DIS_CONNECT" // 网络没有连接
        const val NET_CONNECT_WIFI = "ACTION_NET_CONNECT_WIFI" // 网络连接为wifi
        const val NET_CONNECT_DATA = "ACTION_NET_CONNECT_DATA" // 网络连接为数据网络
    }

    private lateinit var connectManager: ConnectivityManager   // 管理工厂
    private lateinit var netInfo: NetworkInfo   // 网络消息体

    override fun onReceive(context: Context?, intent: Intent?) {
        intent?.action?.let {
            when (it) {
                CONNECTIVITY_ACTION -> {
                    connectManager =
                        context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                  val info=  connectManager.activeNetworkInfo
                    if (info != null) {
                        netInfo = info
                    }  else {
                        // 无网络
                        sendNetworkStateBroadCast(NET_DIS_CONNECT)
                        return
                    }
                    if (netInfo.isAvailable) {
                        //网络已经连接
                        sendNetworkStateBroadCast(NET_CONNECT)
                        when (netInfo.typeName) {
                            "WIFI" -> {
                                sendNetworkStateBroadCast(NET_CONNECT_WIFI)
                            }
                            "MOBILE" -> {
                                sendNetworkStateBroadCast(NET_CONNECT_DATA)
                            }
                        }
                    }
                }
                else ->{}
            }
        }
    }

    private fun sendNetworkStateBroadCast(action: String?) {
        val intent = Intent(action)
        LocalBroadcastUtils.sendBroadCast(context.applicationContext, intent)
    }
}