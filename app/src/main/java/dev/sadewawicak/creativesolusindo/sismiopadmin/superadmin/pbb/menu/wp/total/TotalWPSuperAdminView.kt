package dev.sadewawicak.creativesolusindo.sismiopadmin.superadmin.pbb.menu.wp.total

import dev.sadewawicak.creativesolusindo.sismiopadmin.model.DataModel

interface TotalWPSuperAdminView {

    fun hideLoading()
    fun showLoading()
    fun getResponse(text:String)
    fun getDataKecamatan(dataKecamatan:List<DataModel?>?)
    fun getTotalWPKecamatan(totalwp:Float,totalwpbayar:Float)

}