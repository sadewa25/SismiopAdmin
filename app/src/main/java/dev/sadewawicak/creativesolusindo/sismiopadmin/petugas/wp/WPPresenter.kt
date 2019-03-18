package dev.sadewawicak.creativesolusindo.sismiopadmin.petugas.wp

import android.content.Context
import dev.sadewawicak.creativesolusindo.sismiopadmin.R
import dev.sadewawicak.creativesolusindo.sismiopadmin.client.APIClient
import dev.sadewawicak.creativesolusindo.sismiopadmin.model.ResponseJSON
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WPPresenter(val context: Context?, val client: APIClient, val view: WPView): AnkoLogger {

    private var totalWP:Float = 0f
    private var totalWPBayar:Float = 0f

    fun getTotalWP(kel:String){
        view.showLoading()
        client.totalWP(kel).enqueue(object : Callback<ResponseJSON> {
            override fun onFailure(call: Call<ResponseJSON>, t: Throwable) {
                info("${context?.getString(R.string.title_failure)} ${t.toString()}")
                view.hideLoading()
            }

            override fun onResponse(call: Call<ResponseJSON>, response: Response<ResponseJSON>) {
                if (response.isSuccessful){
                    if (response.body() != null){
                        totalWP = response.body()?.result?.get(0)?.total!!.toFloat()
                        getTotalWPBayar(kel)
                    }
                }
            }

        })
    }

    fun getTotalWPBayar(kel:String){
        client.totalWPBayar(kel).enqueue(object : Callback<ResponseJSON> {
            override fun onFailure(call: Call<ResponseJSON>, t: Throwable) {
                info("${context?.getString(R.string.title_failure)} ${t.toString()}")
                view.hideLoading()
            }

            override fun onResponse(call: Call<ResponseJSON>, response: Response<ResponseJSON>) {
                if (response.isSuccessful){
                    if (response.body() != null){
                        totalWPBayar = response.body()?.result?.get(0)?.total!!.toFloat()
                        view.getWP(totalWP,totalWPBayar)
                        view.hideLoading()
                    }
                }
            }

        })
    }

}