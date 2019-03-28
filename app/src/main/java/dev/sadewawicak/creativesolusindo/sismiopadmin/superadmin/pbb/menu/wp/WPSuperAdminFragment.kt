package dev.sadewawicak.creativesolusindo.sismiopadmin.superadmin.pbb.menu.wp


import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import dev.sadewawicak.creativesolusindo.sismiopadmin.R
import org.jetbrains.anko.find

class WPSuperAdminFragment : Fragment() {

    private lateinit var presenter: WPSuperAdminPresenter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_wpsuper_admin, container, false)

        presenter = WPSuperAdminPresenter(context)

        val pager: ViewPager = view.find(R.id.viewpager_home)
        val tabs: TabLayout = view.find(R.id.tabs_home)

        presenter.setupViewPager(pager,fragmentManager)
        tabs.setupWithViewPager(pager)


        return view
    }


}
