package dev.sadewawicak.creativesolusindo.sismiopadmin.superadmin.topmenu

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import dev.sadewawicak.creativesolusindo.sismiopadmin.R
import dev.sadewawicak.creativesolusindo.sismiopadmin.login.LoginActivity
import dev.sadewawicak.creativesolusindo.sismiopadmin.session.SessionManager
import kotlinx.android.synthetic.main.activity_profile.*
import org.jetbrains.anko.startActivity

class ProfileActivity : AppCompatActivity() {

    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        sessionManager = SessionManager(applicationContext)

        profile_username.text = sessionManager.getUsername()
        profile_logout.setOnClickListener {
            sessionManager.removeData()
            startActivity<LoginActivity>()
            finish()
        }

    }
}
