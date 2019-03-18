package dev.sadewawicak.creativesolusindo.sismiopadmin.superadmin.menu.sppt.jumlah

import dev.sadewawicak.creativesolusindo.sismiopadmin.model.DataModel

interface JumlahSPPTSuperAdminView {

    fun hideLoading()
    fun showLoading()
    fun getResponse(text:String)
    fun getDataKecamatan(dataKecamatan:List<DataModel?>?)
    fun getJumlahSPPTKecamatan(totalsppt:Float,totalspptbayar:Float)

}