package dev.sadewawicak.creativesolusindo.sismiopadmin.login

import android.content.Context
import dev.sadewawicak.creativesolusindo.sismiopadmin.R
import dev.sadewawicak.creativesolusindo.sismiopadmin.client.APIClient
import dev.sadewawicak.creativesolusindo.sismiopadmin.model.ResponseJSON
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginPresenter (val context: Context, val view: LoginView, val client: APIClient): AnkoLogger {

    fun getLogin(username:String,password:String,level:String){
        view.showLoading()
        if (checkNotEmpty(username,password))
            client.loginUsers(username,password,level).enqueue(object : Callback<ResponseJSON> {
                override fun onFailure(call: Call<ResponseJSON>, t: Throwable) {
                    info("${context?.getString(R.string.message_failure)} ${t.toString()}")
                    view.hideLoading()
                }

                override fun onResponse(call: Call<ResponseJSON>, response: Response<ResponseJSON>) {
                    if (response.isSuccessful){
                        if (response.body()!=null){
                            if (response.body()?.result?.isEmpty()!!)
                                view.showResponse(context?.getString(R.string.warning_username_password))
                            else
                                view.getData(response?.body()?.result)
                            view.hideLoading()
                        }
                    }
                }

            })
    }

    fun checkNotEmpty(username: String,password: String):Boolean{
        var check:Boolean = true
        if (username.isEmpty()||password.isEmpty()){
            view.showResponse(context?.getString(R.string.message_empty))
            check = false
        }
        return check
    }


}