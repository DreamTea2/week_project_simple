package com.zsd.codelab

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

/**
 * gradlew app:dependencies
 * 在 Terminal -> 遍历所有的依赖版本 , 用来解决版本报错
 */
class CodeMainActivity : AppCompatActivity() {
     override fun onCreate(savedInstanceState: Bundle?) {
          super.onCreate(savedInstanceState)
          setContentView(R.layout.activity_codelable)
     }
}