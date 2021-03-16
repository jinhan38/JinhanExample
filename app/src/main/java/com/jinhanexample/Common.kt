package com.jinhanexample

import android.R
import android.animation.ObjectAnimator
import android.content.Context
import android.util.Log
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.view.animation.Interpolator
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class Common {

    companion object {

        fun getDP(context: Context, value: Int): Float {
            return TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                value.toFloat(),
                context.resources.displayMetrics
            )
        }

        fun getSP(context: Context, value: Int): Float {
            return TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_SP,
                value.toFloat(),
                context.resources.displayMetrics
            )
        }

        fun makeAnimation(
            v: View?,
            type: String?,
            value: Float,
            duration: Int,
            interpolator: Interpolator,
        ): ObjectAnimator? {
            val anim = ObjectAnimator.ofFloat(v, type, value)
            anim.duration = duration.toLong()
            anim.interpolator = interpolator
            return anim
        }


        fun setColor(context: Context, color: Int): Int {
            return context.resources.getColor(color, null)
        }

        /**
         * 토스트 layout_gravity center 정렬
         *
         * @param context
         * @param msg
         */
        fun setToastCenter(context: Context?, msg: String?) {
            val toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT)
            toast.setGravity(Gravity.CENTER, 0, 0)
            val v = toast.view!!.findViewById<View>(R.id.message) as TextView
            if (v != null) v.gravity = Gravity.CENTER
            toast.show()
        }


        /** * 토스트 텍스트 gravity center
         */
        fun setToastGravityCenter(context: Context?, msg: String?) {
            val toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT)
            val v = toast.view!!.findViewById<View>(R.id.message) as TextView
            if (v != null) v.gravity = Gravity.CENTER
            toast.show()

//        Toast toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
//        toast.setGravity(Gravity.BOTTOM, Gravity.CENTER_HORIZONTAL, 0);
//        toast.show();
        }


        fun getJsonDataFromAsset(context: Context, fileName: String): String? {
            val jsonString: String
            try {
                jsonString = context.assets.open(fileName).bufferedReader().use {
                    it.readText()
                }
            } catch (e: Exception) {
                Log.d("getJsonDataFromAsset", "getJsonDataFromAsset: $e ")
                return null
            }

            return jsonString

        }


        /**
         * 입력받은 StringDate와 simpleDateFormat을 이용해 dayNum(요일 Int값) 구하기
         */
        fun getDayNum(stringDate: String, simpleDateFormat: SimpleDateFormat): Int {
            val cal = Calendar.getInstance()
            //            val transFormat = SimpleDateFormat("yyyy-MM-dd")
            var to: Date? = Date()
            try {
                to = simpleDateFormat.parse(stringDate)
                cal.time = to
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }
            return cal[Calendar.DAY_OF_WEEK]
        }


        /**
         * dayNum(Int)를 String 요일 텍스트로 변환
         */
        fun getDayOfWeekString(dayNum: Int): String? {
            var dayOfWeek = ""
            when (dayNum) {
                1 -> dayOfWeek = "일요일"
                2 -> dayOfWeek = "월요일"
                3 -> dayOfWeek = "화요일"
                4 -> dayOfWeek = "수요일"
                5 -> dayOfWeek = "목요일"
                6 -> dayOfWeek = "금요일"
                7 -> dayOfWeek = "토요일"
            }
            return dayOfWeek
        }
    }


    /**
     * 이번달 일자 구해오기
     *
     * @return
     */
    //    @RequiresApi(api = Build.VERSION_CODES.O)
    //    public static int getDayCountOfThisMonth() {
    //        Date date = new Date();
    //        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
    //        String time = sf.format(date);
    //        YearMonth dayCount = YearMonth.from(LocalDate.parse(time, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    //
    //        return dayCount.lengthOfMonth();
    //    }


    /**
     * 해당월의 마지막 일자 구하기
     * @return
     */
    fun getDayCountOfMonth(year: Int, month: Int): Int {
        val cal = Calendar.getInstance()
        cal[year, month - 1] = 1 // 월은 0부터 시작
        return cal.getActualMaximum(Calendar.DAY_OF_MONTH)
    }


    /**
     * 난수 구하기
     */
    fun randomIntGenerator(min: Int, max: Int): Int {
        return (Math.random() * (max - min) + min).toInt()
    }


    /**
     * 이번달 String으로 반환
     *
     * @return
     */
    fun getTodayMonth(): String? {
        val calendar = Calendar.getInstance()
        var month = (calendar[Calendar.MONTH] + 1).toString()
        if (month.length == 1) {
            month = "0$month"
        }
        return month
    }


    /**
     * yyyyMMdd 형태로 반환
     * calDate가 +1이면 현재 월에서 다음 월 날짜로 반환
     * calDate가 -1이면 현재 월에서 이전 월 날짜로 반환
     * @return
     */
    fun getDateCalculate(calDate: Int): String? {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_MONTH, calDate)
        val year = calendar[Calendar.YEAR].toString()
        var month = (calendar[Calendar.MONTH] + 1).toString()
        var day = calendar[Calendar.DAY_OF_MONTH].toString()
        if (month.length == 1) {
            month = "0$month"
        }
        if (day.length == 1) {
            day = "0$day"
        }
        return year + "" + month + "" + day
    }
}