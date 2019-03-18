package dev.sadewawicak.creativesolusindo.sismiopadmin.petugas

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import dev.sadewawicak.creativesolusindo.sismiopadmin.R
import dev.sadewawicak.creativesolusindo.sismiopadmin.petugas.profile.ProfileFragment
import dev.sadewawicak.creativesolusindo.sismiopadmin.petugas.sppt.SPPTFragment
import dev.sadewawicak.creativesolusindo.sismiopadmin.petugas.wp.WPFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.nav_sppt -> {
                presenter.changeFragment(supportFragmentManager,
                    SPPTFragment(), R.id.frame_main
                )
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_wp -> {
                presenter.changeFragment(supportFragmentManager,
                    WPFragment(), R.id.frame_main
                )
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_profile -> {
                presenter.changeFragment(supportFragmentManager,
                    ProfileFragment(), R.id.frame_main
                )
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private lateinit var presenter: MainPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        presenter = MainPresenter()
        presenter.changeFragment(supportFragmentManager,
            SPPTFragment(), R.id.frame_main
        )
    }
}
