package com.jinhanexample.recyclerView.dragAndSwipe


//HeaderData 클래스의 생성자는 count : Int
//ParentData 클래스를 상속받고, 생성자로 count 를 보낸다.
class HeaderData(count: Int) : ParentData(count) {

    //추상클래스인 ParentData의 함수 getType를 override한다.
    //static 함수로 설정해높은 TYPE을 해당 클래스에 맞게 리턴한다.
    override fun getType(): Int {
        return TYPE_HEADER
    }

    override fun getData(): Int {
        return count
    }
}