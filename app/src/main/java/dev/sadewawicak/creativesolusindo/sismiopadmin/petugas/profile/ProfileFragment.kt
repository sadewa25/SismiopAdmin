package dev.sadewawicak.creativesolusindo.sismiopadmin.petugas.profile

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

class ProfileFragment : Fragment() {

    private lateinit var profileUsername:MyRobotoTextView
    private lateinit var profileAlamat:MyRobotoTextView
    private lateinit var profileTelepon:MyRobotoTextView
    private lateinit var profileLogout:Button
    private lateinit var sessionManager: SessionManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        profileUsername = view.find(R.id.profile_username)
        profileAlamat = view.find(R.id.profile_alamat)
        profileTelepon = view.find(R.id.profile_telpon)
        profileLogout = view.find(R.id.profile_logout)

        sessionManager = SessionManager(context)

        profileUsername.text = sessionManager.getUsername()
        profileAlamat.text = sessionManager.getAlamat()
        profileTelepon.text = sessionManager.getTelpon()

        profileLogout.setOnClickListener {
            sessionManager.removeData()
            startActivity<LoginActivity>()
            activity?.finish()
        }

        return view
    }


}
