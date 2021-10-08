package com.zsd.codelab.list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zsd.codelab.R
import com.zsd.codelab.list.Fruit.Companion.SERVICE_DEMO
import com.zsd.codelab.list.chat.SimpleChatActivity
import com.zsd.codelab.list.service.MyServiceActivity
import com.zsd.codelab.obserable.OnItemClickListener

/**
 * gradlew app:dependencies
 * 在 Terminal -> 遍历所有的依赖版本 , 用来解决版本报错
 */
class CodeMainActivity : AppCompatActivity() {

    private val TAG = "CodeMainActivity"
    private val dataList = ArrayList<Fruit>()
    private lateinit var rvFruit: RecyclerView
    private lateinit var adapter: FruitAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_codelable)
        fruitData()
        initCompounds()
        addListener()
    }

    //初始化水果
    private fun fruitData() {
        repeat(5) {
            dataList.add(Fruit("聊天", R.drawable.fruit_1))
            dataList.add(Fruit("新闻", R.drawable.fruit_2))
            dataList.add(Fruit("服务Test", R.drawable.fruit_5,Fruit.SERVICE_DEMO))
        }
    }

    private fun initCompounds() {
        //初始化相关组件
        rvFruit = findViewById(R.id.rv_fruit)
        val linearManger = LinearLayoutManager(this)
        adapter = FruitAdapter(this, dataList)
        rvFruit.layoutManager = linearManger
        rvFruit.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
        rvFruit.adapter = adapter

        findViewById<Button>(R.id.btn_next).setOnClickListener {
            /*  开启葫芦娃套爷爷模式*/
            HorizonListActivity.newInstance(this, HorizonListActivity::class.java)
        }
    }

    private fun addListener() {
        adapter?.apply {
            addOnItemClickListener(object : OnItemClickListener {
                override fun <Fruit> setOnItemClickListener(t: Fruit) {
                   val fruit =  t as com.zsd.codelab.list.Fruit
                    when(fruit.type) {
                        SERVICE_DEMO -> {
                            MyServiceActivity.newInstance(this@CodeMainActivity,MyServiceActivity::class.java)
                        }
                         else  -> {
                             SimpleChatActivity.newInstance(this@CodeMainActivity,SimpleChatActivity::class.java)
                         }
                    }
                }
            })
        }
    }

}