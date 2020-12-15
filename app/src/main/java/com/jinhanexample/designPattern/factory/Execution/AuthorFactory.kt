package com.jinhanexample.designPattern.factory.Execution

import com.jinhanexample.designPattern.factory.abstractMethod.AuthorBook
import com.jinhanexample.designPattern.factory.abstractMethod.AuthorName
import com.jinhanexample.designPattern.factory.authorClass.*


/**
 * 앞서 만든 클래스들을 이용하여
 * 책 이름을 입력받고, 작가의 이름을 돌려주는 기능을 실현할 클래스입니다.
 * AuthorBook 클래스에는 getBookName 메소드가 있습니다.
 *
 * 1. getBookName 메소드를 통해 책 이름을 입력받고,
 * 2. 이에 해당되는 클래스들을 AuthorName class로 업캐스팅하여 생성
 * 3. 생성한 클래스 반환
 *
 * 작가 클래스들은 AuthorName 클래스를 상속받았기 때문에 AuthorName 클래스로 업캐스팅 가능
 */
class AuthorFactory : AuthorBook() {

    override fun getBookName(bookName: String): AuthorName {

        var authorName: AuthorName? = null

        when (bookName) {
            "방법서설" -> {
                authorName = Descartes()
            }
            "순수이성비판" ->{
                authorName = Kant()
            }
            "향연" ->{
                authorName = Plato()
            }
            "의지와표상의로서의세계" ->{
                authorName = Schopenhauer()
            }
            "에티카" ->{
                authorName = Spinoza()
            }
        }

        return authorName!!

    }
}