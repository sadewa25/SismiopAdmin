package dev.sadewawicak.creativesolusindo.sismiopadmin.superadmin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import dev.sadewawicak.creativesolusindo.sismiopadmin.R
import dev.sadewawicak.creativesolusindo.sismiopadmin.superadmin.menu.ProfileSuperAdminFragment
import dev.sadewawicak.creativesolusindo.sismiopadmin.superadmin.menu.sppt.SPPTSuperAdminFragment
import dev.sadewawicak.creativesolusindo.sismiopadmin.superadmin.menu.wp.WPSuperAdminFragment
import kotlinx.android.synthetic.main.activity_main.*

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
            R.id.nav_profile -> {
                presenter.changeFragment(supportFragmentManager,
                    ProfileSuperAdminFragment(), R.id.frame_main
                )
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private lateinit var presenter: MainActivitySuperAdminPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        presenter = MainActivitySuperAdminPresenter()
        presenter.changeFragment(supportFragmentManager,
            SPPTSuperAdminFragment(), R.id.frame_main
        )
    }

}
