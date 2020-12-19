package com.jinhanexample.animation.progress

import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.jinhanexample.databinding.ActivityValueAnimProgressBarBinding


class ValueAnimProgressBarActivity : AppCompatActivity() {

    lateinit var b: ActivityValueAnimProgressBarBinding
    var thumbLeftMargin: Float = 0f

    //ViewBinding 사용
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityValueAnimProgressBarBinding.inflate(layoutInflater)
        setContentView(b.root)
        b.button.setOnClickListener {
            animationExecute()
        }
    }

    /**
     * view의 width값은 view가 그려진 후에 구해올 수 있습니다.
     * View가 그려신 시점에서 애니메이션을 실행시키고 싶다면 onWindowFocusChanged에서 해주세요
     */
    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)

        thumbLeftMargin =
            (b.progressCircle.layoutParams as FrameLayout.LayoutParams).leftMargin.toFloat()
    }


    private fun animationExecute() {


        //첫번째 프로그레스 ImageView 세팅
        b.bgProgress1.setImageDrawable(linearGradientDrawable(Color.parseColor("#8c8c8c"), 10))
        b.progress1.setImageDrawable(linearGradientDrawable(Color.parseColor("#43d45f"), 10))
        //애니메이션 실행
        setProgressAnimator(
            b.progress1, b.bgProgress1.width.toFloat(),
            getDP(applicationContext, 20), 50, 1000
        )


        //두번째 프로그레스 imageView 세팅
        b.bgProgress2.setImageDrawable(linearGradientDrawable(Color.parseColor("#8c8c8c"), 10))
        b.progress2.setImageDrawable(linearGradientDrawable(Color.parseColor("#ff3636"), 10))
        b.progressCircle.setImageDrawable(linearGradientDrawable(Color.parseColor("#e61616"), 10))

        //두번째 프로그레스 width value animator 실행
        setProgressAnimator(
            b.progress2,
            b.bgProgress2.width.toFloat(),
            getDP(applicationContext, 20),
            80,
            1000
        )

        //두번째 프로그레스의 thumb View animation
        setProgressThumb(
            b.progressCircle,
            b.bgProgress2.width.toFloat(),
            getDP(applicationContext, 20),
            80,
            1000
        )
    }


    /**
     * progress valueAnimator 세팅
     * interploatr는 뒷부분이 빨라지는 AccelerateInterpolator를 defalult로 설정
     */
    private fun setProgressAnimator(
        Progress: View,
        bgWidth: Float,
        progressWidth: Float,
        score: Int,
        duration: Long
    ) {
        val animator = ValueAnimator.ofInt(score)
        animator.duration = duration
        animator.interpolator = AccelerateInterpolator()
        animator.addUpdateListener { animation ->
            val value = animation.animatedValue as Int
            var width: Float = (value * bgWidth) / 100f

            if (width > progressWidth) {
                var param: FrameLayout.LayoutParams =
                    Progress.layoutParams as FrameLayout.LayoutParams
                param.width = width.toInt()
                Progress.layoutParams = param
            }
        }
        animator.start()
    }

    /**
     * Second progress Thumb view animation
     */
    private fun setProgressThumb(
        thumb: View,
        bgWidth: Float,
        thumbWidth: Float,
        score: Int,
        duration: Long
    ) {

        var leftMargin = getDP(applicationContext, 30).toInt()

        val animator = ValueAnimator.ofInt(score)
        animator.duration = duration
        animator.interpolator = AccelerateInterpolator()
        animator.addUpdateListener { animation ->
            val value = animation.animatedValue as Int
            var width: Float = (value * bgWidth) / 100f

            //layoutParams에서 width값이 아닌 leftMargin값을 주었습니다.
            //leftMargin 값에서 thumbView 본인의 width값을 빼주세요(20)
            //그래야 progress width의 오른쪽 끝지점과 thumbView 오른쪽 끝지점이 일치합니다.
            if (width > thumbWidth) {
                var params: FrameLayout.LayoutParams =
                    thumb.layoutParams as FrameLayout.LayoutParams
                params.leftMargin =
                    width.toInt() - getDP(applicationContext, 20).toInt() + leftMargin
                thumb.layoutParams = params
            }
        }
        animator.start()


    }


    /**
     * GradientDrawable을 이용해 progress의 radius와 stroke 등 기타 특성들을 설정할 수 있습니다.
     * GradientDrawable 클래스는 Drawable을 상속받았습니다.
     */
    @SuppressLint("WrongConstant")
    private fun linearGradientDrawable(color: Int, radius: Int): GradientDrawable {
        return GradientDrawable().apply {
            setColor(color)
            cornerRadius = getDP(this@ValueAnimProgressBarActivity, radius)
//            setStroke(3, Color.parseColor("#000000")) // stroke 설정 코드
        }
    }


    /**
     * colors의 속성을 이용해 여러 컬러를 혼합할 수 있습니다.
     * 여기서는 사용하지 않았습니다.
     */
    private fun linearGradientDrawableColors(): GradientDrawable {
        return GradientDrawable().apply {
            colors = intArrayOf(
                Color.parseColor("#008000"),
                Color.parseColor("#8DB600"),
                Color.parseColor("#D0FF14")
            )
            gradientType = GradientDrawable.LINEAR_GRADIENT
            shape = GradientDrawable.OVAL
            orientation = GradientDrawable.Orientation.BOTTOM_TOP

        }
    }

    private fun getDP(context: Context, value: Int): Float {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            value.toFloat(),
            context.resources.displayMetrics
        )
    }

}