package com.jinhanexample.animation

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.jinhanexample.R
import kotlinx.android.synthetic.main.activity_locked_bottom_sheet.*

class LockedBottomSheetActivity : AppCompatActivity() {


    lateinit var bottomSheetBehavior: CustomBottomSheetBehavior<View>
    lateinit var viewBottomSheet: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_locked_bottom_sheet)

        viewBottomSheet = findViewById(R.id.locked_bottom_sheet)

        bottomSheetBehavior = CustomBottomSheetBehavior.from(viewBottomSheet)

        //android bottomsheet Illegal state argument: 5 에러가 발생할 때
        bottomSheetBehavior.isHideable = true

        bottomSheetExpand.setOnClickListener {
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
        }
        bottomSheetHidden.setOnClickListener {
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
        }
    }
}