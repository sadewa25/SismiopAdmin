package dev.sadewawicak.creativesolusindo.sismiopadmin.superadmin.menu.sppt.jumlah

import android.content.Context
import dev.sadewawicak.creativesolusindo.sismiopadmin.R
import dev.sadewawicak.creativesolusindo.sismiopadmin.client.APIClient
import dev.sadewawicak.creativesolusindo.sismiopadmin.model.ResponseJSON
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class JumlahSPPTSuperAdminPresenter (
    val context: Context?,
    val client: APIClient,
    val superAdminView:JumlahSPPTSuperAdminView): AnkoLogger {

    fun getDataKecamatan() {
        superAdminView.showLoading()
        client.getDataKecamatan().enqueue(object : Callback<ResponseJSON> {
            override fun onFailure(call: Call<ResponseJSON>, t: Throwable) {
                info("${context?.getString(R.string.title_failure)} ${t.toString()}")
                superAdminView.hideLoading()
            }

            override fun onResponse(call: Call<ResponseJSON>, response: Response<ResponseJSON>) {
                if (response.isSuccessful) {
                    if (response.body() != null) {
                        superAdminView.getDataKecamatan(response.body()?.result)
                        superAdminView.hideLoading()
                    }
                }
            }

        })
    }

    private var totalSPPT: Float = 0f
    private var totaSPPTBayar: Float = 0f

    fun getTotalSPPT(kec: String) {
        superAdminView.showLoading()
        client.jumlahSPPTSuperAdmin(kec).enqueue(object : Callback<ResponseJSON> {
            override fun onFailure(call: Call<ResponseJSON>, t: Throwable) {
                info("${context?.getString(R.string.title_failure)} ${t.toString()}")
                superAdminView.hideLoading()
            }

            override fun onResponse(call: Call<ResponseJSON>, response: Response<ResponseJSON>) {
                if (response.isSuccessful) {
                    if (response.body() != null) {
                        totalSPPT = response.body()?.result?.get(0)?.jumlah!!.toFloat()
                        getTotalSPPTBayar(kec)
                    }
                }
            }

        })
    }

    fun getTotalSPPTBayar(kec: String) {
        superAdminView.showLoading()
        client.jumlahSPPTSuperAdminBayar(kec).enqueue(object : Callback<ResponseJSON> {
            override fun onFailure(call: Call<ResponseJSON>, t: Throwable) {
                info("${context?.getString(R.string.title_failure)} ${t.toString()}")
                superAdminView.hideLoading()
            }

            override fun onResponse(call: Call<ResponseJSON>, response: Response<ResponseJSON>) {
                if (response.isSuccessful) {
                    if (response.body() != null) {
                        totaSPPTBayar = response.body()?.result?.get(0)?.jumlah!!.toFloat()
                        superAdminView.getJumlahSPPTKecamatan(totalSPPT, totaSPPTBayar)
                        superAdminView.hideLoading()
                    }
                }
            }

        })
    }
}