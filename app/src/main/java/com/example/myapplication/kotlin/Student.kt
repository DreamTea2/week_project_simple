package com.example.myapplication.kotlin

import java.sql.DriverManager.println

/**
 *  学生类   ： 主构造函数的写法
 */
class Student(val sno: String, val grade: Int, name: String, age: Int) : Person(name, age) , Study{
    init {
        println("son is" + sno)
        println("grade is " + grade)
        /**
         * Kotlin 中给我提供了一个Init构造体，所有的构造函数中要求的所有参数。
         */
    }

    constructor(name: String, age: Int) : this("", 20, name, age) {
        // 次构造函数
    }

    constructor() : this("", 0) {
        // 任何一个类中只能有一个主构造函数，但是可以有多个次构造函数。次构造函数也可以用于实例化一个类
        // 这一点和主构造函数没有什么不同，只不过它是有函数体的。
        // Kotlin 规定，当一个类，既有主构造函数又有次构造函数时，所有的次构造函数都必须调用主构造函数（包括间接调用）
        // 这里的例子就是终结式.  可以自行拆分练习.
        // 还有最关键的，所有的次构造函数都是通过 constructor 函数来构造的
    }

    override fun readBooks() {
        println(name + "is readBooks")
    }

    override fun doHomework() {
        println(name+"is doing homework")
    }

}