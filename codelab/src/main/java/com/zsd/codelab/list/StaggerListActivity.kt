package com.zsd.codelab.list

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.zsd.codelab.R
import java.lang.StringBuilder
import kotlin.random.Random

/**
 * 横向滑动的Rv
 */
class StaggerListActivity : AppCompatActivity() {

    private val TAG = "HorizonListActivity"

    private val dataList = ArrayList<Fruit>()
    private lateinit var rvFruit: RecyclerView

    companion object {
        fun newInstance(context: Context, clazz: Class<StaggerListActivity>) {
            val intent = Intent(context, clazz)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stangger_layout)
        fruitData()
        initCompounds()
    }


    private fun initCompounds() {
        //初始化相关组件
        rvFruit = findViewById(R.id.rv_stagger)
        /**
         * reverseLayout 是否开启倒叙加载
         */
        val linearManger = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        val adapter = FruitStaggerAdapter(this, dataList)
        rvFruit.layoutManager = linearManger
        rvFruit.adapter = adapter

    }

    //初始化水果
    private fun fruitData() {
        repeat(5) {
            dataList.add(Fruit(randomString("Orange"), R.drawable.fruit_1))
            dataList.add(Fruit(randomString("Mango"), R.drawable.fruit_2))
            dataList.add(Fruit(randomString("Banner"), R.drawable.fruit_5))
            dataList.add(Fruit(randomString("Book"), R.drawable.fruit_5))
            dataList.add(Fruit(randomString("Cherry"), R.drawable.fruit_5))
            dataList.add(Fruit(randomString("Watermelon"), R.drawable.fruit_5))
        }
    }

    private fun randomString(string: String): String {
        val builder = StringBuilder()
        val n = (1..20).random()
        repeat(n) {
            builder.append(string)
        }
        return builder.toString()
    }


}
