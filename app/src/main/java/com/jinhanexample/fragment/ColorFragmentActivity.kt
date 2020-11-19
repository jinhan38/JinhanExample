package com.jinhanexample.fragment

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.jinhanexample.R
import kotlin.random.Random

class ColorFragmentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_color_fragment)

        //XML에서 프래그먼트 가져오기
        var colorFragment =
            supportFragmentManager.findFragmentById(R.id.color_frag) as ColorFragment
        var colorFragment2 =
            supportFragmentManager.findFragmentById(R.id.color_frag2) as ColorFragment

        colorFragment.setColor(Color.BLUE)
        colorFragment2.setColor(Color.CYAN)


        // 동적으로 프래그먼트 추가
        var colorFragment3 = ColorFragment()
        supportFragmentManager.beginTransaction()
            .add(R.id.container, colorFragment3)
            .commit()


    }

    /**
     *
     * 이대로 프래그먼트를 교체하면 null이다.
     * 왜냐면 생성자가 비어있고, onCreatView에 도달하지 못했다.
     *
     * 버튼 클릭하면 프래그먼트를 변경하고,
     * 배경색이 랜덤하게 설정된다.
     */
    fun onClick(view: View) {

        var newColorFragment = ColorFragment()
        var color: Int

        var random = Random
        color = Color.rgb(
            random.nextInt(255),
            random.nextInt(255),
            random.nextInt(255)
        )

        Toast.makeText(this, "버튼 클릭, color : $color", Toast.LENGTH_SHORT).show()

        supportFragmentManager.beginTransaction()
            .replace(R.id.container, newColorFragment)
            .commit()
        newColorFragment.setColor(color)
    }


}