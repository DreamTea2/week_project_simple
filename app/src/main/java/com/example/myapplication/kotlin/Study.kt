package com.example.myapplication.kotlin

import java.sql.DriverManager.println

/**
 *  Kotlin  中接口
 *
 *  Kotlin 中包含四种 可见性修饰符 ： public  ，private ，protected , internal 需要使用的时候直接定义在 fun 关键字的前面即可。
 *  private     表示对当前类内部可见
 *  public      表示对所有类都可见
 *  protected   表示当前类，子类和同一包路径下的类可见
 *  internal    表示同一模块中的类可见
 *
 *
 *  Kotlin 中的默认的修饰关键词是 public 而java 中的是default
 *
 */
interface Study {
    fun readBooks()
    fun doHomework() {
        println ("do homework default implementation")
    }
}