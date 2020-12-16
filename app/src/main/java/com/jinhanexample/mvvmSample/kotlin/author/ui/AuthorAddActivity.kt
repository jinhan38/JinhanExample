package com.jinhanexample.mvvmSample.kotlin.author.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.jinhanexample.R
import com.jinhanexample.mvvmSample.kotlin.author.data.AuthorEntity
import com.jinhanexample.mvvmSample.kotlin.author.data.AuthorViewModel
import kotlinx.android.synthetic.main.activity_author_add.*

class AuthorAddActivity : AppCompatActivity() {

    companion object {
        const val AUTHOR_NAME = "AUTHOR_NAME"
        const val AUTHOR_BOOKS = "AUTHOR_BOOKS"
        const val AUTHOR_NATION = "AUTHOR_NATION"
    }

    lateinit var authorViewModel: AuthorViewModel
    private var id: Long? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_author_add)

        authorViewModel = ViewModelProvider(this).get(AuthorViewModel::class.java)

        // intent null check & get extras
        if (intent != null && intent.hasExtra(AUTHOR_NAME) && intent.hasExtra(AUTHOR_BOOKS)
            && intent.hasExtra(AUTHOR_NATION)
        ) {
            inputName.setText(intent.getStringExtra(AUTHOR_NAME))
            inputBooks.setText(intent.getStringExtra(AUTHOR_BOOKS))
            inputNation.setText(intent.getStringExtra(AUTHOR_BOOKS))
            id = intent.getLongExtra(AUTHOR_NATION, -1)
        }


        addButton.setOnClickListener {
            val name = inputName.text.toString()
            val books = inputBooks.text.toString()
            val nation = inputNation.text.toString()

            val authorEntity = AuthorEntity(null, name, books, nation)
            authorViewModel.insert(authorEntity = authorEntity)
            finish()
        }


    }
}