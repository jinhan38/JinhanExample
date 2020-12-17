package com.jinhanexample.mvvmSample.java.author.ui;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.jinhanexample.databinding.ActivityAuthorAdd2Binding;
import com.jinhanexample.databinding.ActivityAuthorAddBinding;
import com.jinhanexample.mvvmSample.java.author.data.AuthorEntity;

public class AuthorAddActivity extends AppCompatActivity {

    private ActivityAuthorAddBinding b;
    private AuthorViewModel authorViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityAuthorAddBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());

        authorViewModel = new ViewModelProvider(this).get(AuthorViewModel.class);

        b.addButton.setOnClickListener(view -> {
            authorViewModel.insert(new AuthorEntity(
                    b.inputName.getText().toString(),
                    b.inputBooks.getText().toString(),
                    b.inputNation.getText().toString()));

            finish();
        });

    }
}
