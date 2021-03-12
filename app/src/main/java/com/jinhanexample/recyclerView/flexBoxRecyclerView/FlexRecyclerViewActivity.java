package com.jinhanexample.recyclerView.flexBoxRecyclerView;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.flexbox.AlignItems;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;
import com.jinhanexample.R;

import java.util.ArrayList;

public class FlexRecyclerViewActivity extends AppCompatActivity {

    private static final String TAG = "FlexRecyclerViewActivit";
    private FlexRecyclerViewAdapter flexRecyclerViewAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flexbox_recycler_view_activity);

        Log.d(TAG, "onCreate: 진입");
        RecyclerView recyclerView = findViewById(R.id.flex_box_recyclerView);

        FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(this);
        layoutManager.setFlexWrap(FlexWrap.WRAP);
        layoutManager.setAlignItems(AlignItems.BASELINE);
        layoutManager.setJustifyContent(JustifyContent.CENTER);
        recyclerView.setLayoutManager(layoutManager);

        ArrayList<String> dataList = new ArrayList<>();
        dataList.add("asdfasdfasdf");
        dataList.add("asasdf");
        dataList.add("asdfasdf");
        dataList.add("asdfasddf");
        dataList.add("asda222222222fasdfasdf");
        dataList.add("asdfasdfvvvasdf");
        dataList.add("asdfasxcvdfasdf");
        dataList.add("asdfsdf");
        dataList.add("asdf12341234sdf");
        dataList.add("asdf123412341234sdf");
        dataList.add("asdfㅁㄴㅇ라ㅓㄴ이ㅏ런ㅇsdf");
        dataList.add("asdfㅍㅁㅍㅍㅍsdf");
        dataList.add("asㄹㄹㄹㄹ");
        dataList.add("asdㅁㅁㅁㅁㅁfsdf");

        flexRecyclerViewAdapter = new FlexRecyclerViewAdapter(dataList);
        recyclerView.setAdapter(flexRecyclerViewAdapter);

    }




}
