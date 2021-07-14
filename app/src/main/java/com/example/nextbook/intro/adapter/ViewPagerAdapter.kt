package com.example.nextbook.intro.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.example.nextbook.R
import com.example.nextbook.intro.model.OnBoardingData

class ViewPagerAdapter(private var context: Context, private var onBoardingDataList: List<OnBoardingData>): PagerAdapter() {
    override fun getCount(): Int {
        return onBoardingDataList.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(context).inflate(R.layout.on_boarding_layout, null)
        val illustrationImage: ImageView
        val title: TextView
        val description: TextView

        illustrationImage = view.findViewById(R.id.imageView)
        title = view.findViewById(R.id.onBoardingTitle)
        description = view.findViewById(R.id.onBoardingDescription)

        illustrationImage.setImageResource(onBoardingDataList[position].image)
        title.text = onBoardingDataList[position].title
        description.text = onBoardingDataList[position].desc

        container.addView(view)
        return view
    }
}