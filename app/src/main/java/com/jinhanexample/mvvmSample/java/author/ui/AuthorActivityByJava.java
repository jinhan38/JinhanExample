package com.jinhanexample.mvvmSample.java.author.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.jinhanexample.databinding.ActivityAuthorByJavaBinding;
import com.jinhanexample.mvvmSample.java.author.data.AuthorEntity;
import com.jinhanexample.mvvmSample.java.author.listener.LongClickListener;

public class AuthorActivityByJava extends AppCompatActivity implements LongClickListener {

    private ActivityAuthorByJavaBinding b;
    private AuthorViewModel authorViewModel;
    private AuthorRecyclerViewAdapter authorRecyclerViewAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        b = ActivityAuthorByJavaBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());

        authorViewModel = new ViewModelProvider(this).get(AuthorViewModel.class);

        authorRecyclerViewAdapter = new AuthorRecyclerViewAdapter(this);

        b.authorRecyclerView.setHasFixedSize(true);
        b.authorRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        b.authorRecyclerView.setAdapter(authorRecyclerViewAdapter);

        authorViewModel.getAll().observe(this, authorEntities -> {
            authorRecyclerViewAdapter.addItem(authorEntities);
        });

        b.addButton.setOnClickListener(view -> {
            startActivity(new Intent(this, AuthorAddActivity.class));
        });


    }


    private void deleteDialog(AuthorEntity authorEntity) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setMessage("해당 작가 정보를 삭제하시겠습니까?")
                .setPositiveButton("네", (dialogInterface, i) -> {
                    authorViewModel.delete(authorEntity);
                }).setNegativeButton("아니오", null);
        dialog.show();
    }

    @Override
    public void onLongClickListener(AuthorEntity authorEntity) {
        deleteDialog(authorEntity);
    }


}
