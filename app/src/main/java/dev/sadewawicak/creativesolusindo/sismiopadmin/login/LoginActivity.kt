package dev.sadewawicak.creativesolusindo.sismiopadmin.login

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import dev.sadewawicak.creativesolusindo.sismiopadmin.petugas.MainActivity
import dev.sadewawicak.creativesolusindo.sismiopadmin.R
import dev.sadewawicak.creativesolusindo.sismiopadmin.client.APIResponse
import dev.sadewawicak.creativesolusindo.sismiopadmin.model.DataModel
import dev.sadewawicak.creativesolusindo.sismiopadmin.session.SessionManager
import dev.sadewawicak.creativesolusindo.sismiopadmin.superadmin.MainActivitySuperAdmin
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class LoginActivity : AppCompatActivity(), LoginView,AnkoLogger{

    override fun showLoading() {
        loading.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        loading.visibility = View.GONE
    }

    override fun getData(data: List<DataModel?>?) {
        if (data != null){
            if (!data.get(0)?.idLogin.toString().equals("null")){
                sessionManager.setIDUser(data?.get(0)?.idlogin.toString())
                sessionManager.setLoggedin(true)
                if(data?.get(0)?.kDKECAMATAN != null){
                    sessionManager.setIDPetugas(data?.get(0)?.idPetugas.toString())
                    sessionManager.setUsername(data?.get(0)?.username)
                    sessionManager.setKDKecamatan(data?.get(0)?.kDKECAMATAN)
                    sessionManager.setKDKelurahan(data?.get(0)?.kDKELURAHAN)
                    sessionManager.setAlamat(data?.get(0)?.alamatPetugas)
                    sessionManager.setTelpon(data?.get(0)?.tlpPetugas)
                    startActivity<MainActivity>()
                }
                else if(data?.get(0)?.idAdmin != null){
                    sessionManager.setUsername(data?.get(0)?.namaAdmin)
                    //idAdmin
                    sessionManager.setIDPetugas(data?.get(0)?.idAdmin.toString())
                    startActivity<MainActivitySuperAdmin>()
                }

                finish()
            }else{
                toast(getString(R.string.warning_username_password))
            }
        }
    }

    override fun showResponse(text: String) {
        toast(text)
    }

    private lateinit var presenter: LoginPresenter
    private lateinit var sessionManager: SessionManager
    private var level:String = "petugas"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        sessionManager = SessionManager(this)
        presenter = LoginPresenter(this,this,APIResponse().response())

        var listLevel:ArrayList<String> = arrayListOf()
        listLevel.add("petugas")
        listLevel.add("superadmin")

        var adapterLevelSpinner = ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,listLevel)
        login_level.adapter = adapterLevelSpinner
        login_level.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                level = listLevel.get(position)
            }
        }

        if (sessionManager.loggedin()!!){
            if (!sessionManager.getKDKec().equals("0"))
                startActivity<MainActivity>()
            else
                startActivity<MainActivitySuperAdmin>()

            finish()
        }

        next_login.setOnClickListener {
            presenter.getLogin(login_username.text.toString(),login_password.text.toString(),level)
//            presenter.getLogin("4dm1n","admind2creative",level)
        }

    }

}
