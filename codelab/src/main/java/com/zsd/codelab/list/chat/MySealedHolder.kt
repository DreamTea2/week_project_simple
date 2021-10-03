package com.zsd.codelab.list.chat

import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * 根据Kotlin 代码规范，推荐使用密封类   关键字 Sealed 这样能使代码更加安全与标准\
 */
sealed class MySealedHolder (itemView : View) :RecyclerView.ViewHolder(itemView)
