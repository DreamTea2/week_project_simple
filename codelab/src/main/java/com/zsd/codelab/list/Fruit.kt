package com.zsd.codelab.list

import android.graphics.drawable.Drawable

data class Fruit(val fruit: String? = "appName", val fruitIcon: Int,val type:Int =-1) {
    companion object {
        const val  SERVICE_DEMO = 3
    }
}
