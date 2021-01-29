package com.example.myapplication.base;

import android.view.View;

/**
 * 作品人:create By shaoDong on 2021/1/27 14: 23
 * 邮箱：myu17635728243@163.com
 * note: 哪里没有朴素、善良和真理，哪里也就谈不上有伟大.
 * desc:
 *
 * @author item 点击事件
 **/
public interface onItemClickListener< T > {
    void setOnItemClickListener ( T t, int position, View view);
}
