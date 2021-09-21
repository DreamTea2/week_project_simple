package com.zsd.codelab.list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zsd.codelab.R

/**
 * gradlew app:dependencies
 * 在 Terminal -> 遍历所有的依赖版本 , 用来解决版本报错
 */
class CodeMainActivity : AppCompatActivity() {

    private val TAG = "CodeMainActivity"
    private val dataList = ArrayList<Fruit>()
    private lateinit var rvFruit: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_codelable)
        fruitData()
        initCompounds()
    }

    private fun initCompounds() {
        //初始化相关组件
        rvFruit = findViewById(R.id.rv_fruit)
        val linearManger = LinearLayoutManager(this)
        val adapter = FruitAdapter(this, dataList)
        rvFruit.layoutManager = linearManger
        rvFruit.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
        rvFruit.adapter = adapter

        findViewById<Button>(R.id.btn_next).setOnClickListener {
            HorizonListActivity.newInstance(this, HorizonListActivity::class.java)
        }
    }

    //初始化水果
    private fun fruitData() {
        repeat(5) {
            dataList.add(Fruit("橙子", R.drawable.fruit_1))
            dataList.add(Fruit("菠萝", R.drawable.fruit_2))
//            dataList.add(Fruit("牛油果",R.drawable.fruit_3))
//            dataList.add(Fruit("火龙果",R.drawable.fruit_4)) 图片太大了
            dataList.add(Fruit("香蕉", R.drawable.fruit_5))
        }
    }
}