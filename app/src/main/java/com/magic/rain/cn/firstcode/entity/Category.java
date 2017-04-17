package com.magic.rain.cn.firstcode.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2017/3/12.
 * @author magicRain
 */

public class Category implements Parcelable{

    private int id;

    private String categoryName;

    private int categoryCode;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(int categoryCode) {
        this.categoryCode = categoryCode;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(categoryName);
        dest.writeInt(categoryCode);
    }

    public static final Parcelable.Creator<Category> CREATOR = new Parcelable.Creator<Category>() {

        @Override
        public Category createFromParcel(Parcel source) {
            Category category = new Category();
            category.setId(source.readInt());
            category.setCategoryName(source.readString());
            category.setCategoryCode(source.readInt());
            return category;
        }

        @Override
        public Category[] newArray(int size) {
            return new Category[0];
        }
    };

}
