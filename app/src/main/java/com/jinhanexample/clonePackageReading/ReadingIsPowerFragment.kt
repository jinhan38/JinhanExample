package com.jinhanexample.clonePackageReading

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.jinhanexample.R
import com.jinhanexample.databinding.FragmentReadingIsPowerBinding


class ReadingIsPowerFragment : Fragment(), View.OnClickListener {

    lateinit var b: FragmentReadingIsPowerBinding
    lateinit var bundle: Bundle

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        b = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.fragment_reading_is_power,
            container,
            false
        )


        setupListener()

        return b.root
    }

    private fun setupListener() {
        b.tvAdvantageFirst.setOnClickListener(this)
        b.tvAdvantageSecond.setOnClickListener(this)
        b.tvAdvantageFourth.setOnClickListener(this)
        b.tvAdvantageThird.setOnClickListener(this)

    }

    override fun onClick(p0: View?) {
        when (p0) {
            b.tvAdvantageFirst -> {
                bundle = Bundle()
                bundle.putString("title", "Advantage of reading 1")
                bundle.putString("content", "Logical power improvement")
                findNavController().navigate(
                    R.id.action_readingIsPowerFragment_to_readingAdvantageFragment,
                    bundle
                )
            }
            b.tvAdvantageSecond -> {
                bundle = Bundle()
                bundle.putString("title", "Advantage of reading 2")
                bundle.putString("content", "Improve Concentration")
                findNavController().navigate(
                    R.id.action_readingIsPowerFragment_to_readingAdvantageFragment,
                    bundle
                )

            }
            b.tvAdvantageFourth -> {
                bundle = Bundle()
                bundle.putString("title", "Advantage of reading 3")
                bundle.putString("content", "Forming a habit of study")
                findNavController().navigate(
                    R.id.action_readingIsPowerFragment_to_readingAdvantageFragment,
                    bundle
                )

            }
            b.tvAdvantageThird -> {
                bundle = Bundle()
                bundle.putString("title", "Advantage of reading 4")
                bundle.putString("content", "Accumulation of knowledge")
                findNavController().navigate(
                    R.id.action_readingIsPowerFragment_to_readingAdvantageFragment,
                    bundle
                )

            }

        }
    }

}