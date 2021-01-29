package com.example.myapplication.base;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 作品人:create By shaoDong on 2021/1/27 14: 44
 * 邮箱：myu17635728243@163.com
 * note: 哪里没有朴素、善良和真理，哪里也就谈不上有伟大.
 * desc:
 *
 * @author
 **/
public  class ItemData implements Parcelable {
    private int id ;
    private String itemContent;
    private boolean isCheck;

    public ItemData ( int id, String itemContent, boolean isCheck ) {
        this.id = id;
        this.itemContent = itemContent;
        this.isCheck = isCheck;
    }

    protected ItemData ( Parcel in ) {
        id = in.readInt ( );
        itemContent = in.readString ( );
        isCheck = in.readByte ( ) != 0;
    }


    public static final Creator< ItemData > CREATOR = new Creator< ItemData > ( ) {
        @Override
        public ItemData createFromParcel ( Parcel in ) {
            return new ItemData ( in );
        }

        @Override
        public ItemData[] newArray ( int size ) {
            return new ItemData[ size ];
        }
    };

    @Override
    public int describeContents ( ) {
        return 0;
    }

    @Override
    public void writeToParcel ( Parcel dest, int flags ) {
        dest.writeInt ( id );
        dest.writeString ( itemContent );
        dest.writeByte ( ( byte ) ( isCheck ? 1 : 0 ) );
    }

    public int getId ( ) {
        return id;
    }

    public void setId ( int id ) {
        this.id = id;
    }

    public String getItemContent ( ) {
        return itemContent;
    }

    public void setItemContent ( String itemContent ) {
        this.itemContent = itemContent;
    }

    public boolean isCheck ( ) {
        return isCheck;
    }

    public void setCheck ( boolean check ) {
        isCheck = check;
    }

    public static Creator< ItemData > getCREATOR ( ) {
        return CREATOR;
    }
}
