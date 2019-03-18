package dev.sadewawicak.creativesolusindo.sismiopadmin.petugas.sppt

import android.content.Context
import dev.sadewawicak.creativesolusindo.sismiopadmin.R
import dev.sadewawicak.creativesolusindo.sismiopadmin.client.APIClient
import dev.sadewawicak.creativesolusindo.sismiopadmin.model.ResponseJSON
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SPPTPresenter(val context: Context?,val client:APIClient, val view:SPPTView):AnkoLogger{

    private var totalSPPT:Float = 0f
    private var totaSPPTBayar:Float = 0f

    fun getTotalSPPT(kel:String){
        view.showLoading()
        client.totalSPPT(kel).enqueue(object : Callback<ResponseJSON> {
            override fun onFailure(call: Call<ResponseJSON>, t: Throwable) {
                info("${context?.getString(R.string.title_failure)} ${t.toString()}")
                view.hideLoading()
            }

            override fun onResponse(call: Call<ResponseJSON>, response: Response<ResponseJSON>) {
                if (response.isSuccessful){
                    if (response.body() != null){
                        totalSPPT = response.body()?.result?.get(0)?.total!!.toFloat()
                        getTotalSPPTBayar(kel)
                    }
                }
            }

        })
    }

    fun getTotalSPPTBayar(kel:String){
        view.showLoading()
        client.totalSPPTBayar(kel).enqueue(object : Callback<ResponseJSON> {
            override fun onFailure(call: Call<ResponseJSON>, t: Throwable) {
                info("${context?.getString(R.string.title_failure)} ${t.toString()}")
                view.hideLoading()
            }

            override fun onResponse(call: Call<ResponseJSON>, response: Response<ResponseJSON>) {
                if (response.isSuccessful){
                    if (response.body() != null){
                        totaSPPTBayar = response.body()?.result?.get(0)?.total!!.toFloat()
                        view.getSPPT(totalSPPT,totaSPPTBayar)
                        view.hideLoading()
                    }
                }
            }

        })
    }



}