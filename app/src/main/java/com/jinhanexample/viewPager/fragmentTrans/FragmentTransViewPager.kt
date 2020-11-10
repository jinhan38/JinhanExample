package com.jinhanexample.viewPager.fragmentTrans

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentTransaction
import com.jinhanexample.R
import com.jinhanexample.databinding.ActivityFragmentTransViewPagerBinding
import com.jinhanexample.viewPager.fragmentTrans.fragments.GameFragment
import com.jinhanexample.viewPager.fragmentTrans.fragments.MarketFragment
import com.jinhanexample.viewPager.fragmentTrans.fragments.MyPageFragment
import com.jinhanexample.viewPager.fragmentTrans.fragments.PortFolioFragment

class FragmentTransViewPager : AppCompatActivity() {

    lateinit var b : ActivityFragmentTransViewPagerBinding
    lateinit var transaction: FragmentTransaction

    companion object{
        private const val TAG = "FragmentTransViewPager"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = DataBindingUtil.setContentView(this, R.layout.activity_fragment_trans_view_pager)

        transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, GameFragment.getInstance(), "game").commit()

        setupLister()
        

    }


    private fun setupLister() {

        b.navView.setOnNavigationItemSelectedListener { item ->
            transaction = supportFragmentManager.beginTransaction()


            when (item.itemId) {
                R.id.navigation_game -> {
                    Log.d(TAG, "setupLister: 게임 클릭")
                    transaction.replace(R.id.fragment_container, GameFragment.getInstance(), "game")
                }
                R.id.navigation_market -> {
                    transaction.replace(
                        R.id.fragment_container,
                        MarketFragment.getInstance(),
                        "market"
                    )

                }
                R.id.navigation_portfolio -> {
                    transaction.replace(
                        R.id.fragment_container,
                        PortFolioFragment.getInstance(),
                        "portfolio"
                    )

                }
                R.id.navigation_mypage -> {
                    transaction.replace(
                        R.id.fragment_container,
                        MyPageFragment.getInstance(),
                        "mypage"
                    )
                }
            }

            //addToBackStack(null)을 추가하면 back 버튼이 먹음
//            transaction.addToBackStack(null)
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            transaction.commit()

            true
        }
    }
}