package com.jinhanexample.customView.compoundView

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.jinhanexample.R
import com.jinhanexample.databinding.ActivityStatusViewBinding

class StatusView : ConstraintLayout {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    private var ui: ActivityStatusViewBinding

    init {
        ActivityStatusViewBinding.inflate(LayoutInflater.from(context), this, true).also {
            ui = it
        }
    }

    
    fun setStatus(status: Status) = when (status) {
        Status.SUCCESS -> {
            ui.status.text = context.getString(R.string.success)
            ui.icon.setImageResource(R.drawable.ic_success)
        }
        Status.ERROR -> {
            ui.status.text = context.getString(R.string.error)
            ui.icon.setImageResource(R.drawable.ic_error)
        }
    }

    enum class Status {
        SUCCESS,
        ERROR
    }
}