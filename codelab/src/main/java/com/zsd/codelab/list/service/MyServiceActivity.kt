package com.zsd.codelab.list.service

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.zsd.codelab.R
import com.zsd.codelab.list.chat.SimpleChatActivity

/**
 * Create By shaodong on 2021/10/8 10:36
 */
class MyServiceActivity : AppCompatActivity() {
    private val TAG = "MyServiceActivity"

    companion object {
        fun newInstance(context: Context, clazz: Class<MyServiceActivity>) {
            val intent = Intent(context, clazz)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service)

        val start: Button = findViewById(R.id.btn_service)
        registerBroadCast()
        start.setOnClickListener {
            // 开启服务做一些想做的事事情 *.*~
            val intent = Intent(this,  NetService().javaClass)
            startService(intent)
        }

        val stop: Button = findViewById(R.id.btn_end_service)
        stop.setOnClickListener {
            // 突然不想玩了, 那就踢开ta
            NetworkBroadcastReceiverHelper.unRegister(this)
            val stopIntent = Intent(this,  NetService().javaClass)
            stopService(stopIntent)
        }
    }

    private fun registerBroadCast() {
        NetworkBroadcastReceiverHelper.register(this, object : OnObservableNetListener {
            override fun onActionConnect() {
                Toast.makeText(this@MyServiceActivity, "网络已经连接", Toast.LENGTH_SHORT).show()
            }

            override fun onActionUnConnect() {
                Toast.makeText(this@MyServiceActivity, "无网络连接", Toast.LENGTH_SHORT).show()
            }

            override fun onActionConnectWifi() {
                Toast.makeText(this@MyServiceActivity, "当前为Wifi网络", Toast.LENGTH_SHORT).show()
            }

            override fun onActionConnectData() {
                Toast.makeText(this@MyServiceActivity, "当前为数据网络", Toast.LENGTH_SHORT).show()
            }
        })
    }

}