package com.jinhanexample.designPattern.factory

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jinhanexample.databinding.ActivityFactoryPatternBinding
import com.jinhanexample.designPattern.factory.Execution.AuthorFactory
import com.jinhanexample.designPattern.factory.abstractMethod.AuthorName


class FactoryPatternActivity : AppCompatActivity() {

    //ViewBinding
    lateinit var binding: ActivityFactoryPatternBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //ViewBinding
        binding = ActivityFactoryPatternBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //AuthorFactory는 getBookName 메소드를 이용해 책이름을 입력받고
        //작가(철학자) 클래스를 return하는 역할을 합니다.
        val authorFactory = AuthorFactory()

        binding.Descartes.setOnClickListener {
            //AuthorName 클래스를 상속받았기 때문에 업캐스팅하여 AuthorName 클래스 생성
            // authorName 클래스에서 getAuthorName 메소드로 이름 반환
            val authorName: AuthorName = authorFactory.getBookName("방법서설")
            binding.name.text = authorName.getAuthorName()
        }

        binding.Kant.setOnClickListener {
            val authorName: AuthorName = authorFactory.getBookName("순수이성비판")
            binding.name.text = authorName.getAuthorName()
        }

        binding.Plato.setOnClickListener {
            val authorName: AuthorName = authorFactory.getBookName("향연")
            binding.name.text = authorName.getAuthorName()
        }

        binding.Schopenhauer.setOnClickListener {
            val authorName: AuthorName = authorFactory.getBookName("의지와표상의로서의세계")
            binding.name.text = authorName.getAuthorName()
        }

        binding.Spinoza.setOnClickListener {
            val authorName: AuthorName = authorFactory.getBookName("에티카")
            binding.name.text = authorName.getAuthorName()
        }


    }

}