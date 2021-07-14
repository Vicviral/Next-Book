package com.example.nextbook.intro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.viewpager.widget.ViewPager
import com.example.nextbook.R
import com.example.nextbook.intro.adapter.ViewPagerAdapter
import com.example.nextbook.intro.model.OnBoardingData
import com.google.android.material.tabs.TabLayout

class OnBoarding : AppCompatActivity() {

    var viewPagerAdapter: ViewPagerAdapter? = null
    var tabLayout: TabLayout? = null
    var viewPager: ViewPager? = null
    var next: TextView? = null
    var position = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding)

        tabLayout = findViewById(R.id.tabs)
        next = findViewById(R.id.next)

        val onBoardingData: MutableList<OnBoardingData> = ArrayList()
        onBoardingData.add(OnBoardingData("Hello", "We're coming back live", R.drawable.ic_logo_icon))
        onBoardingData.add(OnBoardingData("Hello", "We're coming back live", R.drawable.ic_logo_icon))
        onBoardingData.add(OnBoardingData("Hello", "We're coming back live", R.drawable.ic_logo_icon))
        onBoardingData.add(OnBoardingData("Hello", "We're coming back live", R.drawable.ic_logo_icon))

        setOnBoardingViewPagerAdapter(onBoardingData)

        position = viewPager!!.currentItem

        next?.setOnClickListener {
            if (position < onBoardingData.size) {
                position++
                viewPager!!.currentItem = position
            }
        }
        tabLayout!!.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                position = tab!!.position
                if (tab.position == onBoardingData.size - 1) {
                    next!!.text = "Get Started"
                    //use data store
                    startActivity(Intent(this@OnBoarding, UserPreference::class.java))
                }else {
                    next!!.text = "Next"
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })
    }

    private fun setOnBoardingViewPagerAdapter(onBoardingData: List<OnBoardingData> ) {
        viewPager = findViewById(R.id.onBoardingViewPager)
        viewPagerAdapter = ViewPagerAdapter(this, onBoardingData)
        viewPager!!.adapter = viewPagerAdapter
        tabLayout!!.setupWithViewPager(viewPager)
    }
}