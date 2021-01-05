package com.jinhanexample.customChart.stockChart

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jinhanexample.R
import kotlinx.android.synthetic.main.activity_stock_chart_trading_view_search.*

class StockChartTradingViewSearch : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stock_chart_trading_view_search)

        btnSearch.setOnClickListener {
            var intent = Intent(this, StockChartActivity::class.java)
            intent.putExtra("keyword", etKeyword.text.toString())
            startActivity(intent)
        }
    }
}