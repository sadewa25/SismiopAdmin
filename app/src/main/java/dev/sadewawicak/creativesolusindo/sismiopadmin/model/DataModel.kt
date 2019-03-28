package dev.sadewawicak.creativesolusindo.sismiopadmin.model


import com.google.gson.annotations.SerializedName

data class DataModel(

	val itemImg:Int = 0,
	val itemText:String="",

	@field:SerializedName("KD_DATI2")
	val kDDATI2: String? = null,

	@field:SerializedName("last_login")
	val lastLogin: String? = null,

	@field:SerializedName("nama_petugas")
	val namaPetugas: String? = null,

	@field:SerializedName("id_petugas")
	val idPetugas: Int? = null,

	@field:SerializedName("id_login")
	val idLogin: Int? = null,

	@field:SerializedName("KD_KELURAHAN")
	val kDKELURAHAN: String? = null,

	@field:SerializedName("password")
	val password: String? = null,

	@field:SerializedName("tlp_petugas")
	val tlpPetugas: String? = null,

	@field:SerializedName("idlogin")
	val idlogin: Int? = null,

	@field:SerializedName("KD_PROPINSI")
	val kDPROPINSI: String? = null,

	@field:SerializedName("alamat_petugas")
	val alamatPetugas: String? = null,

	@field:SerializedName("KD_KECAMATAN")
	val kDKECAMATAN: String? = null,

	@field:SerializedName("username")
	val username: String? = null,

	@field:SerializedName("total")
	val total: Int? = null,

	@field:SerializedName("tlp_admin")
	val tlpAdmin: String? = null,

	@field:SerializedName("id_admin")
	val idAdmin: Int? = null,

	@field:SerializedName("nama_admin")
	val namaAdmin: String? = null,

	@field:SerializedName("KD_KEC")
	val kDKEC: String? = null,

	@field:SerializedName("NAMA_KEC")
	val nAMAKEC: String? = null,

	@field:SerializedName("TOTAL")
	val tOTAL: Int? = null,

	@field:SerializedName("jumlah")
	val jumlah: String? = null
)