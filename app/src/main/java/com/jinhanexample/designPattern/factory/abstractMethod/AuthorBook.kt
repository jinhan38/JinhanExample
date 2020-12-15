package com.jinhanexample.designPattern.factory.abstractMethod

/**
 * AuthorBook 클래스는 책의 이름을 입력받고,
 * AuthorName 클래스를 리턴해주는 역할을  합니다.
 * getBookName 를 구현할 때 입력받는 책 이름에 따라 when 문을 구성할 것입니다.
 */
abstract class AuthorBook {
    abstract fun getBookName(bookName: String): AuthorName
}