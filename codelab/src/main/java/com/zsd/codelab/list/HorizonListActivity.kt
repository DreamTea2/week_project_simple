package com.zsd.codelab.list

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zsd.codelab.R

/**
 * 横向滑动的Rv
 */
class HorizonListActivity : AppCompatActivity() {

    private val TAG = "HorizonListActivity"

    private val dataList = ArrayList<Fruit>()
    private lateinit var rvFruit: RecyclerView

    companion object {
        fun newInstance(context: Context, clazz: Class<HorizonListActivity>) {
             val intent =Intent(context,clazz)
             context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_horizon_list)
        fruitData()
        initCompounds()
    }


    private fun initCompounds() {
        //初始化相关组件
        rvFruit = findViewById(R.id.rv_horizon)
        /**
         * reverseLayout 是否开启倒叙加载
         */
        val linearManger = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val adapter = FruitHorizonAdapter(this, dataList)
        rvFruit.layoutManager = linearManger
        rvFruit.adapter = adapter

        findViewById<Button>(R.id.btn_next).setOnClickListener {
            StaggerListActivity.newInstance(this, StaggerListActivity::class.java)
        }
    }

    //初始化水果
    private fun fruitData() {
        repeat(5) {
            dataList.add(Fruit("橙子", R.drawable.fruit_1))
            dataList.add(Fruit("菠萝", R.drawable.fruit_2))
            dataList.add(Fruit("香蕉", R.drawable.fruit_5))
        }
    }


}
