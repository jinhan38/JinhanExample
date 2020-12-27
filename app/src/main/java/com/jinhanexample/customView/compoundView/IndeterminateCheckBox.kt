package com.jinhanexample.customView.compoundView

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatCheckBox
import com.jinhanexample.R

class IndeterminateCheckBox : AppCompatCheckBox {
    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    init { setState(State.UNCHECKED) }

    fun setState(state: State) = setButtonDrawable(
        when (state) {
            State.UNCHECKED -> R.drawable.ic_checkbox_unchecked
            State.INDETERMINATE -> R.drawable.ic_checkbox_indeterminate
            State.CHECKED -> R.drawable.ic_checkbox_checked
        }
    )

    enum class State { UNCHECKED, INDETERMINATE, CHECKED }
}