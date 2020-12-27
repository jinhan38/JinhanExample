package com.jinhanexample.customChart.stockChart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebSettings
import android.webkit.WebViewClient
import com.jinhanexample.R
import kotlinx.android.synthetic.main.activity_stock_chart.*

class StockChartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stock_chart)
        browserSetting()

    }

    private fun browserSetting() {
        val webSetting = webView.settings
        webView.webViewClient = WebViewClient() // 클릭시 새창 안뜨게
        webSetting.javaScriptEnabled = true //자바스크립트 허용 여부
        webSetting.setSupportMultipleWindows(false) //새창 띄우기 허용 여부
        webSetting.javaScriptCanOpenWindowsAutomatically = false //자바스크립트 새창 띄우기 허용 여부
        webSetting.loadWithOverviewMode = true //메타태그 허용여부
        webSetting.useWideViewPort = true //화면 사이즈 맞추기 허용 여부
        webSetting.setSupportZoom(true) // 줌 허용여부
        webSetting.builtInZoomControls = true //화면 확대 축소 허용여부
        webSetting.layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN //컨텐츠 사이즈 맞추기
//        webView.loadUrl("https://charting-library.tradingview.com/mobile_white.html")
        webView.loadUrl("https://kr.tradingview.com/chart/wKGHQv3b/")
    }
}