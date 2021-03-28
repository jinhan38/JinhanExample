package com.jinhanexample.viewPager.touchTestViewPager.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.jinhanexample.databinding.TouchListenerTestRecycleverItemBinding
import com.jinhanexample.viewPager.touchTestViewPager.ui.TouchTestChildPagerFragment
import com.jinhanexample.viewPager.touchTestViewPager.ui.TouchTestViewPagerFragment
import kotlinx.android.synthetic.main.touch_listener_test_recyclever_item.view.*

//class CartListNormalAdapter : RecyclerView.Adapter<CartListNormalAdapter.Holder>() {
class TouchTestRecyclerViewAdapter(var fm: FragmentManager) :
    RecyclerView.Adapter<TouchTestRecyclerViewAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: TouchListenerTestRecycleverItemBinding, parent: ViewGroup) :
        RecyclerView.ViewHolder(itemView.root) {
        var parent = parent
        fun bind() {

            val fragmentArray = ArrayList<Fragment>()
            for (i in 0 until 5) {
                fragmentArray.add(TouchTestChildPagerFragment())
            }
            val viewPagerAdapter = ViewPagerAdapter(fm, fragmentArray)
            itemView.viewpager.adapter = viewPagerAdapter
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            TouchListenerTestRecycleverItemBinding.inflate(LayoutInflater.from(parent.context),
                parent, false)
        return ViewHolder(binding, parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int {
        return 1
    }


}