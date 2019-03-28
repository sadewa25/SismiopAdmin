package dev.sadewawicak.creativesolusindo.sismiopadmin.superadmin.pbb.menu.wp.jumlah

import dev.sadewawicak.creativesolusindo.sismiopadmin.model.DataModel

interface JumlahWPSuperAdminView {

    fun hideLoading()
    fun showLoading()
    fun getResponse(text:String)
    fun getDataKecamatan(dataKecamatan:List<DataModel?>?)
    fun getJumlahWPKecamatan(totalwp:Float,totalwpbayar:Float)

}