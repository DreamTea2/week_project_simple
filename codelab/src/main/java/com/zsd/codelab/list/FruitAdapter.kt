package com.zsd.codelab.list

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.zsd.codelab.R
import com.zsd.codelab.obserable.OnItemClickListener

class FruitAdapter(var context: Context?, private val dataList: List<Fruit>? = ArrayList()) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private lateinit var mItemListener: OnItemClickListener

     fun  addOnItemClickListener(itemListener: OnItemClickListener) {
        this.mItemListener = itemListener
    }
    class FruitHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.iv_fruit)
        val textView: TextView = itemView.findViewById(R.id.tv_fruit_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.list_fruit_layout, parent, false)
        return FruitHolder(itemView)
    }

    //  运用运算符  ？： 来避免空指针异常
    override fun getItemCount(): Int = dataList?.size ?: 0;

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is FruitHolder -> {
                dataList?.let {
                    holder.textView.text = dataList[position].fruit
                    holder.imageView.setImageResource(dataList[position].fruitIcon)
                    holder.itemView.setOnClickListener(View.OnClickListener {
                        it?.let {
                            mItemListener.let {
                                mItemListener.setOnItemClickListener(dataList[position])
                            }
                        }
                    })
                }
            }
        }
    }
}