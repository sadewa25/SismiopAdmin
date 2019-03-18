package dev.sadewawicak.creativesolusindo.sismiopadmin.petugas

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction

class MainPresenter {

    fun changeFragment(manager: FragmentManager, fragment: Fragment, layout:Int){
        var transaction: FragmentTransaction = manager.beginTransaction()
        transaction.replace(layout,fragment).commit()
    }

}