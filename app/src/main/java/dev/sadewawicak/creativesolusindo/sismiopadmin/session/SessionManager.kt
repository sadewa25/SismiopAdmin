package dev.sadewawicak.creativesolusindo.sismiopadmin.session

import android.content.Context
import android.content.SharedPreferences

class SessionManager (val context: Context?) {

    // Shared pref mode
    val PRIVATE_MODE = 0

    // Sharedpref file name
    private val PREF_NAME = "SismiopAdmin"

    var pref: SharedPreferences? = context?.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
    var editor: SharedPreferences.Editor? = pref?.edit()

    fun setLoggedin(logggedin: Boolean) {
        editor?.putBoolean("loggedIn", logggedin)
        editor?.commit()
    }

    fun setUsername(user: String?) {
        editor?.putString("username", user)
        editor?.commit()
    }

    fun setIDUser(idUser: String?) {
        editor?.putString("id_user", idUser)
        editor?.commit()
    }

    fun setIDPetugas(idUser: String?) {
        editor?.putString("id_petugas", idUser)
        editor?.commit()
    }

    fun setKDKecamatan(kdkec: String?) {
        editor?.putString("kd_kec", kdkec)
        editor?.commit()
    }
    fun setKDKelurahan(kdkel: String?) {
        editor?.putString("kd_kel", kdkel)
        editor?.commit()
    }

    fun setAlamat(alamat: String?) {
        editor?.putString("alamat", alamat)
        editor?.commit()
    }

    fun setTelpon(telpon: String?) {
        editor?.putString("telpon", telpon)
        editor?.commit()
    }

    fun getAlamat(): String? {
        return pref?.getString("alamat", "-")
    }
    fun getTelpon(): String? {
        return pref?.getString("telpon", "-")
    }

    fun getIDPetugas(): String? {
        return pref?.getString("id_petugas", "")
    }

    fun getIDUser(): String? {
        return pref?.getString("id_user", "kosong")
    }

    fun getKDKec(): String? {
        return pref?.getString("kd_kec", "0")
    }
    fun getKDKel(): String? {
        return pref?.getString("kd_kel", "0")
    }

    fun loggedin(): Boolean? {
        return pref?.getBoolean("loggedIn", false)
    }

    fun getUsername(): String? {
        return pref?.getString("username", "kosong")
    }

    fun removeData() {
        editor?.clear()
        editor?.commit()
    }

}