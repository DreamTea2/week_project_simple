package com.zsd.codelab.list.chat

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.zsd.codelab.R
import com.zsd.codelab.list.HorizonListActivity

class SimpleChatActivity : AppCompatActivity() {
    private val TAG = "SimpleChatActivity"
    private var mChatList = ArrayList<Chat>()
    private lateinit var mChatAdapter: ChatAdapter

    private lateinit var mChatRv: RecyclerView

    companion object {
        fun newInstance(context: Context, clazz: Class<SimpleChatActivity>) {
            val intent = Intent(context, clazz)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.simple_chat)
        initMsg()
        mChatRv = findViewById(R.id.rv_chat)
        mChatAdapter = ChatAdapter(mChatList)
        mChatRv.adapter = mChatAdapter
        addListener()
    }

    private fun initMsg() {
        mChatList = ArrayList()
        val received = Chat("Hello , ShaoDong ,I love Y", Chat.TYPE_RECEIVED)
        val sent = Chat("I love You,too。。", Chat.TYPE_SENT)
        mChatList.add(received)
        mChatList.add(sent)
    }

    private fun addListener() {
        val editInput: EditText = findViewById(R.id.edit_input)
        val sendBtn: Button = findViewById(R.id.btn_send)
        sendBtn.setOnClickListener {
            val content = editInput.text.trim()
            if (content.isNotEmpty()) {
                val sent = Chat("$content", Chat.TYPE_SENT)
                mChatList.add(sent)
                // 单条目刷新
                mChatAdapter.apply {
                    notifyItemInserted(mChatList.size - 1)
                }
                // 将Rv 定位到最后一行
                mChatRv.apply {
                    scrollToPosition(mChatList.size - 1)
                }
                editInput.setText("")
            }
        }
    }
}