package dev.sadewawicak.creativesolusindo.sismiopadmin.superadmin.pbb.menu.sppt

import android.content.Context
import android.support.v4.app.FragmentManager
import android.support.v4.view.ViewPager
import dev.sadewawicak.creativesolusindo.sismiopadmin.R
import dev.sadewawicak.creativesolusindo.sismiopadmin.superadmin.pbb.menu.adapter.ViewPagerAdapter
import dev.sadewawicak.creativesolusindo.sismiopadmin.superadmin.pbb.menu.sppt.jumlah.JumlahSPPTSuperAdminFragment
import dev.sadewawicak.creativesolusindo.sismiopadmin.superadmin.pbb.menu.sppt.total.TotalSPPTSuperAdminSuperAdminFragment


class SPPTSuperAdminPresenter(val context: Context?){

    fun setupViewPager(viewPager: ViewPager, manager: FragmentManager?){
        var adapter: ViewPagerAdapter =
            ViewPagerAdapter(manager)
        adapter.addFragment(TotalSPPTSuperAdminSuperAdminFragment(), context?.getString(R.string.title_total_sppt)!!)
        adapter.addFragment(JumlahSPPTSuperAdminFragment(),context?.getString(R.string.title_jumlah_sppt)!!)
        viewPager.adapter = adapter
    }

}