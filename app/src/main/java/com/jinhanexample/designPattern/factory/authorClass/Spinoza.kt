package com.jinhanexample.designPattern.factory.authorClass

import com.jinhanexample.designPattern.factory.abstractMethod.AuthorName

/**
 * AuthorName 클래스를 상속받고, 작가의 이름을 가지고 있는 클래스입니다.
 */
class Spinoza : AuthorName() {
    override fun getAuthorName(): String {
        return "Spinoza"
    }
}