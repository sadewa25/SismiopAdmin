package dev.sadewawicak.creativesolusindo.sismiopadmin.superadmin.pbb.menu.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

class ViewPagerAdapter (fm: FragmentManager?): FragmentStatePagerAdapter(fm) {

    private var dataFragment:ArrayList<Fragment> = ArrayList()
    private var dataFragmentTitle:ArrayList<String> = ArrayList()

    override fun getItem(position: Int): Fragment {
        return dataFragment.get(position)
    }

    override fun getCount(): Int {
        return dataFragment.size
    }

    fun addFragment(fragment: Fragment, string: String){
        dataFragment.add(fragment)
        dataFragmentTitle.add(string)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return dataFragmentTitle.get(position)
    }

}