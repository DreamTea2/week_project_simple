package com.zsd.codelab.dice_roller

import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.zsd.codelab.R

/**
 * Create By shaodong on 2021/8/30 9:49
 */
class DiceRollerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dice)
        var randomInt:Int
        val resultText = findViewById<TextView>(R.id.tv_roll_content)
        findViewById<Button>(R.id.btn_roll).setOnClickListener {
            randomInt = (1..6).random()
            resultText.text = randomInt.toString()
            rollClick(resultText?.text.toString())
        }
        findViewById<Button>(R.id.btn_reset).setOnClickListener {
            resultText.text = 0.toString()
        }
        //最好的做法是使用字符串资源，而不是在布局中对字符串进行硬编码。
    }

    private fun rollClick(str: String) {
        str.let {
            Toast.makeText(  this, str,
                Toast.LENGTH_SHORT ).show()
        }
    }
}