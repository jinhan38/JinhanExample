package com.jinhanexample.recyclerView.tagView;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.jinhanexample.R;

import java.util.ArrayList;

import co.lujun.androidtagview.TagContainerLayout;
import co.lujun.androidtagview.TagView;

public class TagViewActivityJava extends AppCompatActivity {


    private static final String TAG = "TagViewActivityJava";
    private TagContainerLayout tagContainerLayout;
    private SparseArray<Boolean> tagViewSparseArray = new SparseArray<>();
    private ArrayList<String> dataList = new ArrayList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tag_view);


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

        for (int i = 0; i < dataList.size(); i++) {
            tagViewSparseArray.put(i, false);
        }

        tagContainerLayout = (TagContainerLayout) findViewById(R.id.tagcontainerLayout);
        tagContainerLayout.setTags(dataList);


        tagInit();

        tagContainerLayout.setOnTagClickListener(new TagView.OnTagClickListener() {
            @Override
            public void onTagClick(int position, String text) {
                Log.d(TAG, "onTagClick: 클릭 : " + tagViewSparseArray.get(position));
                if (tagViewSparseArray.get(position)) {
                    tagViewSparseArray.put(position, false);
                    unClickedTagView(tagContainerLayout.getTagView(position));
                } else {
                    tagViewSparseArray.put(position, true);
                    clickedTagView(tagContainerLayout.getTagView(position));
                }

            }

            @Override
            public void onTagLongClick(int position, String text) {
            }

            @Override
            public void onSelectedTagDrag(int position, String text) {
            }

            @Override
            public void onTagCrossClick(int position) {

            }
        });


    }

    private void tagInit() {

        tagContainerLayout.setTagBackgroundColor(Color.WHITE);
        tagContainerLayout.setTagBorderColor(Color.GRAY);
        tagContainerLayout.setTagTextColor(Color.BLACK);

        for (int i = 0; i < dataList.size(); i++) {
            unClickedTagView(tagContainerLayout.getTagView(i));
        }
    }

    private void clickedTagView(TagView tagView) {
        tagView.setTagBackgroundColor(Color.BLACK);
        tagView.setTagTextColor(Color.WHITE);
        tagView.setTagBorderColor(Color.BLACK);
    }

    private void unClickedTagView(TagView tagView) {
        tagView.setTagBackgroundColor(Color.WHITE);
        tagView.setTagTextColor(Color.BLACK);
        tagView.setTagBorderColor(Color.GRAY);
    }
}
