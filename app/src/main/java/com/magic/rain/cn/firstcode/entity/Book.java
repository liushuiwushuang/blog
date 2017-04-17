package com.magic.rain.cn.firstcode.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2017/3/12.
 * @author magicRain
 */

public class Book implements Parcelable{

    private int id;

    private String author;

    private double price;

    private int pages;

    private String name;

    private String press;

    private Category category;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(author);
        dest.writeDouble(price);
        dest.writeInt(pages);
        dest.writeString(name);
        dest.writeString(press);
        dest.writeValue(category);
    }

    public static final Parcelable.Creator<Book> CREATOR = new Parcelable.Creator<Book>() {

        @Override
        public Book createFromParcel(Parcel source) {
            Book book = new Book();
            book.id = source.readInt();
            book.author = source.readString();
            book.price = source.readDouble();
            book.pages = source.readInt();
            book.name = source.readString();
            book.press = source.readString();
            book.category = (Category) source.readValue(Category.class.getClassLoader());
            return book;
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[0];
        }
    };

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", price=" + price +
                ", pages=" + pages +
                ", name='" + name + '\'' +
                ", press='" + press + '\'' +
                ", category=" + category.getId() +
                '}';
    }
}
