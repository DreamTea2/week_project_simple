package com.example.myapplication.kotlin

import java.sql.DriverManager.println

/**
 * 爸爸类
 */
open class Person (val namel :String ,val agel:Int) {
    var name = ""
    var age = 0
    fun eat() {
        println(name + "is eating ,He is" + age + "years old.")
    }
}