package com.jinhanexample.customChart.stockChart

import android.app.DownloadManager
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.KeyEvent
import android.webkit.WebSettings
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.jinhanexample.R
import kotlinx.android.synthetic.main.activity_stock_chart.*
import java.io.File
import java.io.UnsupportedEncodingException
import java.net.URLDecoder

class StockChartActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "StockChartActivity"
        val baseUrl = "https://devapi.lifeplusmentalcare.com:2080/not/NOT01010000.do"
    }

    lateinit var intentKeyword: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stock_chart)

        if (intent.hasExtra("keyword")) {
            intentKeyword = intent.getStringExtra("keyword").toString()
        }
        intentKeyword = "?ntprCustCode=9062971819"

        browserSetting()

    }

    private fun browserSetting() {
        val webSetting = webView.settings
        webView.webViewClient = WebViewClient()
        webSetting.javaScriptEnabled = true //자바스크립트 허용 여부
        webSetting.setSupportMultipleWindows(false) //새창 띄우기 허용 여부
        webSetting.javaScriptCanOpenWindowsAutomatically = true //자바스크립트 새창 띄우기 허용 여부
        webSetting.loadWithOverviewMode = true //메타태그 허용여부
        webSetting.useWideViewPort = true //화면 사이즈 맞추기 허용 여부
        webSetting.setSupportZoom(true) // 줌 허용여부
        webSetting.builtInZoomControls = true //화면 확대 축소 허용여부
        webSetting.layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN //컨텐츠 사이즈 맞추기
//        webView.loadUrl("https://charting-library.tradingview.com/mobile_white.html")
        val url = baseUrl + intentKeyword
        Log.d(TAG, "browserSetting: $url")
        webView.loadUrl(url)

    }


    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {

        if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
            webView.goBack()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    fun startDownload(url: String?, dir: String?, fileName: String?) {

        val file: File = File(getUrlDecode(dir))
        val request = DownloadManager.Request(Uri.parse(url))
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
        request.setTitle(fileName)
        request.setDescription("Downloading file...")
        request.allowScanningByMediaScanner()
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
        request.setDestinationUri(Uri.fromFile(file))
        request.setDestinationInExternalPublicDir(
            Environment.DIRECTORY_DOWNLOADS,
            "" + System.currentTimeMillis()
        )
        request.setAllowedOverMetered(true)
        request.setAllowedOverRoaming(true)
        val manager = getSystemService(DOWNLOAD_SERVICE) as DownloadManager
        manager.enqueue(request)
        Toast.makeText(this, "파일 다운로드가 완료되었습니다.", Toast.LENGTH_SHORT).show()
    }

    fun getUrlDecode(_strFileName: String?): String? {
        var strRet: String? = null
        strRet = try {
            URLDecoder.decode(_strFileName, "UTF-8")
        } catch (e: UnsupportedEncodingException) {
            ""
        }
        return strRet
    }

}