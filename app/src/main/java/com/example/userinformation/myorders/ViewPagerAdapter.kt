package com.example.userinformation.myorders

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter



class ViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val mFragmentList = ArrayList<Fragment>()
    val mFragmentTitleList = ArrayList<String>()

    override fun getItemCount(): Int {
        return mFragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return mFragmentList[position]
    }

    fun addFragment(fragment: Fragment, title: String) {
        mFragmentList.add(fragment)
        mFragmentTitleList.add(title)
    }
}

