package dev.sadewawicak.creativesolusindo.sismiopadmin.login

import dev.sadewawicak.creativesolusindo.sismiopadmin.model.DataModel

interface LoginView {

    fun showLoading()
    fun hideLoading()
    fun getData(data: List<DataModel?>?)
    fun showResponse(text:String)

}