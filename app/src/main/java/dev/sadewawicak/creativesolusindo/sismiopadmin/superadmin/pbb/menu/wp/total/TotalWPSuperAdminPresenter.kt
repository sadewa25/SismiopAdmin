package dev.sadewawicak.creativesolusindo.sismiopadmin.superadmin.pbb.menu.wp.total

import android.content.Context
import dev.sadewawicak.creativesolusindo.sismiopadmin.R
import dev.sadewawicak.creativesolusindo.sismiopadmin.client.APIClient
import dev.sadewawicak.creativesolusindo.sismiopadmin.model.ResponseJSON
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TotalWPSuperAdminPresenter (val context: Context?, val client: APIClient, val superAdminView: TotalWPSuperAdminView):
    AnkoLogger {

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
//                        superAdminView.hideLoading()
                    }
                }
            }

        })
    }

    private var totalSPPT: Float = 0f
    private var totaSPPTBayar: Float = 0f

    fun getTotalWP(kec: String) {
        superAdminView.showLoading()
        client.totalWPSuperAdmin(kec).enqueue(object : Callback<ResponseJSON> {
            override fun onFailure(call: Call<ResponseJSON>, t: Throwable) {
                info("${context?.getString(R.string.title_failure)} ${t.toString()}")
                superAdminView.hideLoading()
            }

            override fun onResponse(call: Call<ResponseJSON>, response: Response<ResponseJSON>) {
                if (response.isSuccessful) {
                    if (response.body() != null) {
                        totalSPPT = response.body()?.result?.get(0)?.total!!.toFloat()
                        getTotalWPBayar(kec)
                    }
                }
            }

        })
    }

    fun getTotalWPBayar(kec: String) {
        superAdminView.showLoading()
        client.totalWPBayarSuperAdmin(kec).enqueue(object : Callback<ResponseJSON> {
            override fun onFailure(call: Call<ResponseJSON>, t: Throwable) {
                info("${context?.getString(R.string.title_failure)} ${t.toString()}")
                superAdminView.hideLoading()
            }

            override fun onResponse(call: Call<ResponseJSON>, response: Response<ResponseJSON>) {
                if (response.isSuccessful) {
                    if (response.body() != null) {
                        totaSPPTBayar = response.body()?.result?.get(0)?.total!!.toFloat()
                        superAdminView.getTotalWPKecamatan(totalSPPT, totaSPPTBayar)
                        superAdminView.hideLoading()
                    }
                }
            }

        })
    }

}