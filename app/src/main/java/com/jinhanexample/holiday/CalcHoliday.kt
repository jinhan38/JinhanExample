package com.jinhanexample.holiday

object CalcHoliday {

    val 일요일 = 1
    val 월요일 = 2
    val 화요일 = 3
    val 수요일 = 4
    val 목요일 = 5
    val 금요일 = 6
    val 토요일 = 7

    /**
     * 주말 체크
     * 평일 : true, 주말 : false
     */
    fun checkHoliday(dayNum: Int): Boolean {
        return when (dayNum) {
            일요일, 토요일 -> false
            else -> true
        }
    }

}