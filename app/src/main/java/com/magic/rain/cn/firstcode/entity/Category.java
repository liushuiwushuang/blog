package com.magic.rain.cn.firstcode.entity;

/**
 * Created by Administrator on 2017/3/12.
 * @author magicRain
 */

public class Category {

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
}
