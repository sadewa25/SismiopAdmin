package dev.sadewawicak.creativesolusindo.sismiopadmin.superadmin.pbb

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import dev.sadewawicak.creativesolusindo.sismiopadmin.R
import dev.sadewawicak.creativesolusindo.sismiopadmin.superadmin.pbb.menu.sppt.SPPTSuperAdminFragment
import dev.sadewawicak.creativesolusindo.sismiopadmin.superadmin.pbb.menu.wp.WPSuperAdminFragment
import kotlinx.android.synthetic.main.activity_main_super_admin.*

class MainActivitySuperAdmin : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.nav_sppt -> {
                presenter.changeFragment(supportFragmentManager,
                    SPPTSuperAdminFragment(), R.id.frame_main
                )
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_wp -> {
                presenter.changeFragment(supportFragmentManager,
                    WPSuperAdminFragment(), R.id.frame_main
                )
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private lateinit var presenter: MainActivitySuperAdminPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_super_admin)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        presenter = MainActivitySuperAdminPresenter()
        presenter.changeFragment(supportFragmentManager,
            SPPTSuperAdminFragment(), R.id.frame_main
        )

        var menu = navigation.menu
        var itemDisable = menu.findItem(R.id.nav_profile)
        itemDisable.isEnabled = false

    }

}
