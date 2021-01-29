package com.example.myapplication.base;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.IdRes;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 作品人:create By shaoDong on 2021/1/27 14: 46
 * 邮箱：myu17635728243@163.com
 * note: 哪里没有朴素、善良和真理，哪里也就谈不上有伟大.
 * desc:
 *
 * @author
 **/
public class BaseHolder extends RecyclerView.ViewHolder {

    private SparseArray< View > sparseArray;

    public BaseHolder ( @NonNull View itemView ) {
        super ( itemView );
    }

    public < V extends View > V getView ( @IdRes int viewId ) {
        if ( sparseArray == null ) {
            sparseArray = new SparseArray<> ( );
        }
        View view = sparseArray.get ( viewId );
        if ( view == null ) {
            view = itemView.findViewById ( viewId );
            sparseArray.put ( viewId, view );
        }
        return ( V ) view;
    }

    public void setText ( @IdRes int viewId, @StringRes int text ) {
        this.< TextView >getView ( viewId ).setText ( text );
    }

    public void setText ( @IdRes int viewId, String text ) {
        if ( TextUtils.isEmpty ( text ) ) {
            text = "数据有误";
        }
        this.< TextView >getView ( viewId ).setText ( text );
    }

    public void setImageBitmap ( @IdRes int viewId, Bitmap bitmap ) {
        this.< ImageView >getView ( viewId ).setImageBitmap ( bitmap );
    }

    public void setImageResource ( @IdRes int viewId, @DrawableRes int resId ) {
        this.< ImageView >getView ( viewId ).setImageResource ( resId );
    }

    public void setBackgroundColor ( @IdRes int viewId, @ColorRes int colorRes, Context context ) {
        this.getView ( viewId ).setBackgroundColor ( ContextCompat.getColor ( context, colorRes ) );
    }

    public void setBackground ( @IdRes int viewId, @DrawableRes int resId, Context context ) {
        this.getView ( viewId ).setBackground ( ContextCompat.getDrawable ( context, resId ) );
    }

    public void setBackgroundAlpha ( @IdRes int viewId,
                                     @IntRange ( from = 0, to = 255 ) int alpha ) {
        this.getView ( viewId ).getBackground ( ).mutate ( ).setAlpha ( alpha );
    }

    public void startScalAnim ( @IdRes int viewId ) {
        Animation animation = new ScaleAnimation ( 0.3f, 1.0f, 0.3f, 1.0f );
        animation.setDuration ( 1200 );
        animation.setFillAfter ( true );
        this.getView ( viewId ).startAnimation ( animation );
    }
}
