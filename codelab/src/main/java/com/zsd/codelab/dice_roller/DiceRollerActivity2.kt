package com.zsd.codelab.dice_roller

import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.snackbar.Snackbar
import com.zsd.codelab.R

/**
 * Create By shaodong on 2021/8/30 9:49
 */
class DiceRollerActivity2 : AppCompatActivity() {

    private var randomInt: Int = 0
    private lateinit var diceImage: ImageView
    private lateinit var mRootGroup: ConstraintLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dice_2)

        diceImage = findViewById(R.id.iv_dice)
        mRootGroup = findViewById(R.id.root_group)
        findViewById<Button>(R.id.btn_roll).setOnClickListener {
            randomInt = diceRandom()
            val diceRandomImage = diceRandomImage(randomInt)
            diceImage.setImageResource(diceRandomImage)
            rollClick(randomInt.toString());
        }
        findViewById<Button>(R.id.btn_reset).setOnClickListener {
            val diceRandomImage = diceRandomImage(0)
            diceImage.setImageResource(diceRandomImage)
            rollClick(0.toString());
        }
        //最好的做法是使用字符串资源，而不是在布局中对字符串进行硬编码。
        //理想情况下，您应该尽量减少对的调用次数findViewById(),因为 Android系统每次都在搜索整个视图层次结构,这是一项代价高昂的操作
        // asset 文件采用 AssetManager 访问.
        // raw和res下的视频音频文件采用openRawResource()
        /**
         *
         *   drawable/
        drawable-en/
        drawable-fr-rCA/
        drawable-en-port/
        drawable-en-notouch-12key/
        drawable-port-ldpi/
        drawable-port-notouch-12key/
        语言区域 = en-GB
        屏幕方向 = port
        屏幕像素密度 = hdpi
        触摸屏类型 = notouch
        主要文本输入法 = 12key
         */
    }

    private fun diceRandom(): Int {
        // 函数声明: 获取每一个随机数,用来区分图片
        return (0..6).random()
    }

    private fun diceRandomImage(randomInt: Int) = when (randomInt) {
        // 根据随机数筛选筛子
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        6 -> R.drawable.dice_6
        else -> R.drawable.empty_dice
    }

    private fun rollClick(str: String) {
        Snackbar.make(mRootGroup, str, Snackbar.LENGTH_SHORT).setAction(str, null).show()
    }
}


