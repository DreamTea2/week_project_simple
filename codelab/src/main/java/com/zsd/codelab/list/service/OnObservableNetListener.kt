package com.zsd.codelab.list.service

/**
 * Create By shaodong on 2021/10/8 13:51
 */
interface OnObservableNetListener {
    fun onActionConnect()  //连接网络
    fun onActionUnConnect() //网络连接断开
    fun onActionConnectWifi() // 连接Wifi
    fun onActionConnectData() // 连接数据网络
}