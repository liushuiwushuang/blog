package com.magic.rain.cn.firstcode.entity;

/**
 * Created by Administrator on 2017/3/9.
 * @author magicRain
 */

public class Fruit {

    private String name;

    private int imageId;

    public Fruit(String name, int imageId) {
        this.name = name;
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public int getImageId() {
        return imageId;
    }
}
