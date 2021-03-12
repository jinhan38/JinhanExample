package com.jinhanexample.recyclerView.flexBoxRecyclerView;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jinhanexample.R;

public class FlexItemViewHolder extends RecyclerView.ViewHolder {

    private TextView textView;

    public FlexItemViewHolder(@NonNull View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.tvFlexBoxContent);
    }

    void bind(String text) {

        textView.setText(text);
//        textView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//            @Override
//            public void onGlobalLayout() {
//                FlexboxLayoutManager.LayoutParams flexboxLayoutParams =
//                        (FlexboxLayoutManager.LayoutParams) textView.getLayoutParams();
//                flexboxLayoutParams.setFlexGrow(1.0f);
//
//                textView.setText(text);
//                textView.setBackgroundResource(R.drawable.flex_item_background);
//                textView.setGravity(Gravity.CENTER);
//                textView.setLayoutParams(flexboxLayoutParams);
//            }
//        });


    }
}
