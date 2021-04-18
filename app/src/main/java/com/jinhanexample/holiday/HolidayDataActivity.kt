package com.jinhanexample.holiday

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.jinhanexample.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.w3c.dom.Element
import org.w3c.dom.Node
import java.io.BufferedReader
import java.io.ByteArrayInputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*
import javax.xml.parsers.DocumentBuilderFactory
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class HolidayDataActivity : AppCompatActivity() {

    companion object {

        val holidayMap = HashMap<String, String>()
        val holidayList = ArrayList<Holiday>()
        private const val TAG = "HolidayDataActivity"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_holiday_data)
        CoroutineScope(Dispatchers.Default).launch {
            getHolidayData()
        }
    }


    /**
     * 공휴일 정보 공공데이터 API Param 값 세팅
     */
    private fun getHolidayData() {

        val calendar = Calendar.getInstance()
        var year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        Log.d(TAG, "getHolidayData: 이번달 : $month")
        //12월인 경우 내년
        callHolidayData(year)
        if (month == 12) {
            year++
            callHolidayData(year)
        }

    }

    /**
     * 공휴일 정보 공공데이터 API 호출
     */
    private fun callHolidayData(year: Int) {

//        val urlString =
//            "http://apis.data.go.kr/B090041/openapi/service/SpcdeInfoService/getRestDeInfo?solYear=$year" +
//                    "&numOfRows=100" +
//                    "&ServiceKey=c/JQqxGIQgUj8/PNjlROaupkFcrFpePwxwnBFdNhAuTstDYnI/d9q7250hNJZfBF2kNwHptZHb9mwmVHR7sC1g=="
        val urlString =
            "http://apis.data.go.kr/B090041/openapi/service/SpcdeInfoService/getRestDeInfo?solYear=$year" +
                    "&numOfRows=100" +
                    "&ServiceKey={yourKey}"
        val buffer = StringBuilder()

        try {
            var xml = ""
            val url = URL(urlString)
            val conn = url.openConnection() as HttpURLConnection
            if (conn != null) {
                conn.connectTimeout = 10000
                if (conn.responseCode == HttpURLConnection.HTTP_OK) {
                    val isr = InputStreamReader(conn.inputStream)
                    val br = BufferedReader(isr)
                    while (true) {
                        val line = br.readLine() ?: break
                        buffer.append(line)
                    }
                    br.close()
                    conn.disconnect()
                    xml = buffer.toString()
                    Log.d(TAG, "특일 xml : $xml")

                    val factory = DocumentBuilderFactory.newInstance()
                    val documentBuilder = factory.newDocumentBuilder()
                    val ip = ByteArrayInputStream(xml.toByteArray())
                    val doc = documentBuilder.parse(ip)
                    val element = doc.documentElement

                    val itemList = element.getElementsByTagName("item")

                    if (itemList != null && itemList.length > 0) {

                        for (i in 0 until itemList.length) {
                            val n: Node = itemList.item(i)

                            if (n.nodeType == Node.ELEMENT_NODE) {

                                val element = n as Element
                                val map = mutableMapOf<String, String>()

                                for (j in 0 until element.attributes.length) {
                                    map[element.attributes.item(j).nodeName] =
                                        element.attributes.item(j).nodeValue
                                }

                                val dateKind =
                                    element.getElementsByTagName("dateKind")
                                        .item(0).textContent
                                val dateName =
                                    element.getElementsByTagName("dateName")
                                        .item(0).textContent
                                val isHoliday =
                                    element.getElementsByTagName("isHoliday")
                                        .item(0).textContent
                                val locdate =
                                    element.getElementsByTagName("locdate")
                                        .item(0).textContent

                                holidayList.add(Holiday(dateKind,
                                    dateName,
                                    isHoliday,
                                    locdate))

                                //공휴일 hashMap에 저장
                                for (date in holidayList) {
                                    holidayMap[date.locdate] = date.locdate
                                }

                            }
                        }

                    }
                }
            }

            //이번달 주말 hashMap에 저장
            val calendar = Calendar.getInstance()
            calendar.add(Calendar.DAY_OF_MONTH, 1)
            addWeekendDate(calendar)

            //다음달 주말 hashMap에 저장
            calendar.add(Calendar.DAY_OF_MONTH, 1)
            addWeekendDate(calendar)

            Log.d(TAG, "holidayMap : $holidayMap")


        } catch (e: java.lang.Exception) {
            Log.d(TAG, "특일 xml eeeee : $e")
        }
    }

    private fun addWeekendDate(calendar : Calendar){
        val  lastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
        for (i in 0 until lastDay) {
            calendar.set(Calendar.DAY_OF_MONTH, i + 1)
            val dayNum = calendar.get(Calendar.DAY_OF_WEEK)
            if (!CalcHoliday.checkHoliday(dayNum)) {
                val sdf = SimpleDateFormat("yyyyMMdd")
                val date = sdf.format(calendar.time)
                holidayMap[date.toString()] = date.toString()
            }
        }
    }


}