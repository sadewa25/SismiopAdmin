package dev.sadewawicak.creativesolusindo.sismiopadmin.superadmin.menu.wp

import android.content.Context
import android.support.v4.app.FragmentManager
import android.support.v4.view.ViewPager
import dev.sadewawicak.creativesolusindo.sismiopadmin.R
import dev.sadewawicak.creativesolusindo.sismiopadmin.superadmin.menu.adapter.ViewPagerAdapter
import dev.sadewawicak.creativesolusindo.sismiopadmin.superadmin.menu.wp.jumlah.JumlahWPSuperAdminFragment
import dev.sadewawicak.creativesolusindo.sismiopadmin.superadmin.menu.wp.total.TotalWPSuperAdminFragment

class WPSuperAdminPresenter(val context: Context?){

    fun setupViewPager(viewPager: ViewPager, manager: FragmentManager?){
        var adapter: ViewPagerAdapter = ViewPagerAdapter(manager)
        adapter.addFragment(TotalWPSuperAdminFragment(), context?.getString(R.string.title_total_wp)!!)
        adapter.addFragment(JumlahWPSuperAdminFragment(),context?.getString(R.string.title_jumlah_wp)!!)
        viewPager.adapter = adapter
    }

}