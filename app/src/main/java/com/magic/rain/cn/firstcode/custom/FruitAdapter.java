package com.magic.rain.cn.firstcode.custom;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.magic.rain.cn.firstcode.R;
import com.magic.rain.cn.firstcode.entity.Fruit;

import java.util.List;


/**
 * Created by Administrator on 2017/3/9.
 * @author magicRain
 */

public class FruitAdapter extends ArrayAdapter<Fruit> {

    private int resourceId;

    public FruitAdapter(Context context, int resourceId, List<Fruit> objects) {
        super(context, resourceId, objects);
        this.resourceId = resourceId;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView,@NonNull ViewGroup parent) {
        Fruit fruit = getItem(position);
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.findView(convertView);
            convertView.setTag(viewHolder); // 将 ViewHolder 存储在 convertView 中
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.setView(fruit);
        return convertView;
    }

    private class ViewHolder {
        private ImageView fruitImage;
        private TextView fruitName;

        private void findView(View view) {
            fruitImage = (ImageView) view.findViewById(R.id.fruit_image);
            fruitName = (TextView) view.findViewById(R.id.fruit_name);
        }

        private void setView(Fruit fruit) {
            fruitImage.setImageResource(fruit.getImageId());
            fruitName.setText(fruit.getName());
        }
    }

    /*
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        Fruit fruit = (Fruit) getItem(position);
//        View view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
//        ImageView fruitImage = (ImageView) view.findViewById(R.id.fruit_image);
//        TextView fruitName = (TextView) view.findViewById(R.id.fruit_name);
//        fruitImage.setImageResource(fruit.getImageId());
//        fruitName.setText(fruit.getName());
//        return view;

        // 优化
        Fruit fruit = getItem(position);
        View view;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
        } else {
            view  = convertView;
        }
        ImageView fruitImage = (ImageView) view.findViewById(R.id.fruit_image);
        TextView fruitName = (TextView) view.findViewById(R.id.fruit_name);
        fruitImage.setImageResource(fruit.getImageId());
        fruitName.setText(fruit.getName());
        return view;
    }
    */
}
