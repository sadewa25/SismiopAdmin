package dev.sadewawicak.creativesolusindo.sismiopadmin.superadmin.menu


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import customfonts.MyRobotoTextView

import dev.sadewawicak.creativesolusindo.sismiopadmin.R
import dev.sadewawicak.creativesolusindo.sismiopadmin.login.LoginActivity
import dev.sadewawicak.creativesolusindo.sismiopadmin.session.SessionManager
import org.jetbrains.anko.find
import org.jetbrains.anko.support.v4.startActivity

class ProfileSuperAdminFragment : Fragment() {

    private lateinit var sessionManager: SessionManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile_super_admin, container, false)

        sessionManager = SessionManager(context)

        (view.find<MyRobotoTextView>(R.id.profile_username)).text = sessionManager.getUsername()
        (view.find<Button>(R.id.profile_logout)).setOnClickListener {
            sessionManager.removeData()
            startActivity<LoginActivity>()
            activity?.finish()
        }

        return view
    }


}
