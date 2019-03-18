package dev.sadewawicak.creativesolusindo.sismiopadmin.superadmin.menu.sppt.total

import dev.sadewawicak.creativesolusindo.sismiopadmin.model.DataModel

interface TotalSPPTSuperAdminView {

    fun hideLoading()
    fun showLoading()
    fun getResponse(text:String)
    fun getDataKecamatan(dataKecamatan:List<DataModel?>?)
    fun getSPPTKecamatan(totalsppt:Float,totalspptbayar:Float)

}