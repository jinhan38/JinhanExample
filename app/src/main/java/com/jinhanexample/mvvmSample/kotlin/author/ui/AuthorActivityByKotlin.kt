package com.jinhanexample.mvvmSample.kotlin.author.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.jinhanexample.databinding.ActivityAuthorByKotlinBinding
import com.jinhanexample.mvvmSample.kotlin.author.data.AuthorEntity

class AuthorActivityByKotlin : AppCompatActivity() {

    //데이터에 접근할 viewModel을 생성하겠습니다
    lateinit var authorViewModel: AuthorViewModel

    //뷰바인딩 사용
    lateinit var b: ActivityAuthorByKotlinBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityAuthorByKotlinBinding.inflate(layoutInflater)
        setContentView(b.root)

        // AuthorRecyclerViewAdapter 생성자에서 completionBloack을 이용해서
        //AuthorEntity를 전달하도록 했습니다.
        val authorRecyclerViewAdapter = AuthorRecyclerViewAdapter {
            deleteDialog(authorEntity = it)
        }

        b.authorRecyclerView.adapter = authorRecyclerViewAdapter
        b.authorRecyclerView.layoutManager = LinearLayoutManager(this)
        b.authorRecyclerView.setHasFixedSize(true)

        //viewModel 주입, ViewModel은 매번 생성하는 것이 아니라 instance를 반환한다.
        authorViewModel = ViewModelProvider(this).get(AuthorViewModel::class.java)

        //observe를 이용해 authorViewModel에서 가져온 데이터(List<AuthorEntity>) 관찰
        //owner가 this라는 의미는 authorViewModel은 AuthorActivityByKotlin 클래스의 생명주기를 관찰한다는 의미다.
        //AuthorActivityByKotlin클래스가 destory되면 viewModel도 같이 파괴된다.
        authorViewModel.getAll().observe(this@AuthorActivityByKotlin, Observer {
            Log.d("TAG", "onCreate: 옵저버 진입")
            authorRecyclerViewAdapter.addItem(it)
        })

        b.addButton.setOnClickListener {
            val intent = Intent(this, AuthorAddActivity::class.java)
            startActivity(intent)
        }

    }

    fun deleteDialog(authorEntity: AuthorEntity) {
        val builder = AlertDialog.Builder(this)

        builder.setMessage("해당 작가 정보를 삭제하시겠습니까?")
            .setNegativeButton("아니오", null)
            .setPositiveButton("네") { dialog, which ->
                authorViewModel.delete(authorEntity = authorEntity)
            }
        builder.show()

    }
}