package com.example.myapplication.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * 作品人:create By shaoDong on 2021/1/27 14: 01
 * 邮箱：myu17635728243@163.com
 * note: 哪里没有朴素、善良和真理，哪里也就谈不上有伟大.
 * desc:
 *
 * @author
 **/
public abstract class BaseAdapter< VH extends RecyclerView.ViewHolder, T > extends RecyclerView.Adapter< VH > {

    private List< T > dataList;

    private Context context;

    private onItemClickListener< T > itemClickListener;

    public BaseAdapter ( List< T > dataList, Context context ) {
        this.dataList = dataList;
        this.context = context;
    }

    public BaseAdapter ( List< T > dataList, Context context,
                         onItemClickListener< T > clickListener ) {
        this.dataList = dataList;
        this.context = context;
        this.itemClickListener = clickListener;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder ( @NonNull ViewGroup parent, int viewType ) {
        View view = LayoutInflater.from ( context ).inflate ( layoutId ( ), parent, false );
        return getViewHolder ( view );
    }

    protected abstract int layoutId ( );

    @Override
    public void onBindViewHolder ( @NonNull VH holder, int position ) {
        T t = dataList.get ( position );
        setValue ( holder, t );
        holder.itemView.setOnClickListener ( v -> {
            if ( itemClickListener != null ) {
                itemClickListener.setOnItemClickListener ( t, position ,v);
            }
        } );
    }

    @Override
    public int getItemCount ( ) {
        return dataList == null ? 0 : dataList.size ( );
    }

    /**
     * 复用的ViewHolder
     *
     * @param itemView
     * @return
     */
    protected abstract VH getViewHolder ( View itemView );

    /**
     * 添加数据
     *
     * @param data
     * @return
     */
    protected abstract void setValue ( VH holder, T data );

    public void remove ( int position ) {
        if ( dataList != null && dataList.size ( ) > 0 ) {
            dataList.remove ( position );
            notifyItemChanged ( position );
        }
    }

}
