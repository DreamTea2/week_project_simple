package com.example.myapplication.kotlin

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import java.lang.Integer.max
import java.sql.DriverManager.println

public class SimpleActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "SimpleActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple)

        Log.d(Companion.TAG, "onCreate: 调试信息")
        Log.e(Companion.TAG, "onCreate: 警告信息 ")
        Log.i(Companion.TAG, "onCreate: 比较重要的信息")
        Log.w(Companion.TAG, "onCreate: 警告信息")
//       Log.v  并无找到 只找到了四个方法
        println("Hello Kotlin !")
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun largerNumber(num1: Int, num2: Int): Int {
        return max(num1, num2)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun largerNumber2(num1: Int, num2: Int): Int = max(num1, num2)


    fun largerNumber3(num1: Int, num2: Int): Int {
        var value = 0;
        if (num1 > num2) {
            value = num1
        } else {
            value = num2
        }
        return value;
    }

    fun largerNumber4(num1: Int, num2: Int): Int = if (num1 > num2) num1 else num2


    fun getScore(name: String) = if (name == "Tom") {
        86
    } else if (name == "Jim") {
        77
    } else if (name == "Jack") {
        95
    } else if (name == "Lily") {
        100
    } else {
        0
    }

    // 匹配规则  （匹配条件，任意类型） ->{执行逻辑}
    fun getScore2(name: String) = when (name) {
        "Tom" -> 86
        "Jim" -> 77
        "Jack" -> 95
        "Lily" -> 100
        else -> 0
    }

    fun getScore3(name: String) = when {
        name == "Tom" -> 86
        name == "Jim" -> 77
        name == "Jack" -> 95
        name == "Lily" -> 100
        else -> 0
    }


    fun getScor4(name: String) = when {
        name.equals("Tom") -> 86
        name == "Jim" -> 77
        name == "Jack" -> 95
        name == "Lily" -> 100
        else -> 0
    }

    fun checkNumber(num: Number) {
        when (num) {
            is Int -> println("Is Int")
            is Double -> println("Is Double")
            else -> println("number not support")
        }
    }

    // 普通便利循环
    fun moreNumber() {
        for (i in 0..10) {
            println(i.toString())
        }
    }

    // 区间遍历循环
    fun moreNumer2() {
//        for (i in 0 until 10 step 2) {
//          step 代表  i  = i +2 每次循环都 +2
//        } until 代表区间 在 0 到10 以内
    }

    fun moreNumber3() {
//        for (i in 10 downTo 1) {
//            降序遍历 downTo
        //   程序演示为 10 到 1 之间的降序排列
//        }
    }
}