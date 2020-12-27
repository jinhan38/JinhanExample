package com.jinhanexample.customView.compoundView

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jinhanexample.databinding.ActivityCompoundViewBinding

class CompoundViewActivity : AppCompatActivity() {

    lateinit var ui: ActivityCompoundViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ui = ActivityCompoundViewBinding.inflate(layoutInflater).apply {
            setContentView(root)
        }

        ui.statusView.setStatus(StatusView.Status.ERROR)

        ui.indeterminate1.setState(IndeterminateCheckBox.State.INDETERMINATE)
        ui.indeterminate2.setState(IndeterminateCheckBox.State.UNCHECKED)
        ui.indeterminate3.setState(IndeterminateCheckBox.State.CHECKED)

    }
}