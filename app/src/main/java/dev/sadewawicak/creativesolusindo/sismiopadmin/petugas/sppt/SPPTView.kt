package dev.sadewawicak.creativesolusindo.sismiopadmin.petugas.sppt

interface SPPTView {
    fun showLoading()
    fun hideLoading()
    fun getSPPT(totalsppt:Float,totalspptbayar:Float)
}