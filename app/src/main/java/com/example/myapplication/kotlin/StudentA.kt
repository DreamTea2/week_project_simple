package com.example.myapplication.kotlin


/**
 *  无主构造函数
 */
class StudentA : Person {
    constructor(name:String,age:Int) :super (name ,age) {
        // 类中有次构造函数，没有主构造函数。这种情况真的十分少见，但是在Kotlin 是允许的。当一个类没有显示地定义主
        // 构造函数且定义了次构造函数的时候，它就是没有主构造函数。
    }
}