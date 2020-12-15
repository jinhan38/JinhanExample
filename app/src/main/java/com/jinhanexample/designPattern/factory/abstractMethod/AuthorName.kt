package com.jinhanexample.designPattern.factory.abstractMethod


/**
 * getAuthorName 메소드는 최종적으로 작가의 이름을 반환해줄 메소드입니다.
 *
 */
abstract class AuthorName {
    abstract fun getAuthorName(): String
}