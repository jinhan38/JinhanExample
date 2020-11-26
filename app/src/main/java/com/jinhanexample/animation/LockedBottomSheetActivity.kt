package com.jinhanexample.animation

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.jinhanexample.R

class LockedBottomSheetActivity : AppCompatActivity() {


    lateinit var bottomSheetBehavior: BottomSheetBehavior<View>
    lateinit var viewBottomSheet: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_locked_bottom_sheet)

        viewBottomSheet = findViewById(R.id.locked_bottom_sheet)
        bottomSheetBehavior = CustomBottomSheetBehavior.from(viewBottomSheet)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
    }
}