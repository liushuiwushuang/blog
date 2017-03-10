package cn.magic.rain.com.firstcode.activity.uicustom;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cn.magic.rain.com.firstcode.R;
import cn.magic.rain.com.firstcode.custom.RandomHorizontalAdapter;
import cn.magic.rain.com.firstcode.entity.Fruit;

public class RecyclerViewHorizontalActivity extends AppCompatActivity {

    private List<Fruit> fruitList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_horizontal);

        initFruits();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view_horizontal);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        RandomHorizontalAdapter randomHorizontalAdapter = new RandomHorizontalAdapter(fruitList);

        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(randomHorizontalAdapter);
    }

    private void initFruits() {
        for (int i = 0; i < 5; i++) {
            Fruit apple = new Fruit("Apple", R.mipmap.ic_launcher);
            fruitList.add(apple);
            Fruit banana = new Fruit("Banana", R.mipmap.ic_launcher);
            fruitList.add(banana);
            Fruit orange = new Fruit("Orange", R.mipmap.ic_launcher);
            fruitList.add(orange);
        }
    }
}
