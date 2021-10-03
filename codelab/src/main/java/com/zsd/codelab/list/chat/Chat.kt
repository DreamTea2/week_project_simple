package com.zsd.codelab.list.chat

data class Chat(val content: String, val type: Int) {
    companion object {
        // 接受消息
        const val TYPE_RECEIVED = 0
        // 发送消息
        const val TYPE_SENT = 1
    }
}
