package com.zsd.codelab.list.chat

import android.transition.ChangeTransform
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zsd.codelab.R
import kotlin.math.sign

class ChatAdapter(val msgList: List<Chat>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class ChatLeftHolder(itemView: View) : MySealedHolder(itemView) {
        val leftMsg: TextView = itemView.findViewById(R.id.tv_chat_left)
    }

    class ChatRightHolder(itemView: View) : MySealedHolder(itemView) {
        val rightMsg: TextView = itemView.findViewById(R.id.tv_chat_right)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            Chat.TYPE_RECEIVED -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_chat_left, parent, false)
                ChatLeftHolder(view)
            }
            else -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_chat_right, parent, false)
                ChatRightHolder(view)
            }
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ChatLeftHolder ->
                holder.leftMsg.text = msgList[position].content
            is ChatRightHolder ->
                holder.rightMsg.text = msgList[position].content
        }
    }

    override fun getItemViewType(position: Int): Int {
        val chat = msgList[position]
        return chat.type
    }

    override fun getItemCount(): Int = msgList?.let {
        msgList.size
    }


}