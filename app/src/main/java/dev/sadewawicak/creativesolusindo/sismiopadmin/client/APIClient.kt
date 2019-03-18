package dev.sadewawicak.creativesolusindo.sismiopadmin.client

import dev.sadewawicak.creativesolusindo.sismiopadmin.model.ResponseJSON
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface APIClient {

    @FormUrlEncoded
    @POST("api/login_warga")
    fun loginUsers(
        @Field("username")username:String,
        @Field("password")password:String,
        @Field("lvl")lvl:String
    ): Call<ResponseJSON>

    @FormUrlEncoded
    @POST("api/totalwp")
    fun totalWP(
        @Field("kel")kel:String
    ): Call<ResponseJSON>

    @FormUrlEncoded
    @POST("api/totalwpbayar")
    fun totalWPBayar(
        @Field("kel")kel:String
    ): Call<ResponseJSON>

    @FormUrlEncoded
    @POST("api/totalpembayaranspptpetugas")
    fun totalSPPTBayar(
        @Field("kel")kel:String
    ): Call<ResponseJSON>

    @FormUrlEncoded
    @POST("api/totalspptpetugas")
    fun totalSPPT(
        @Field("kel")kel:String
    ): Call<ResponseJSON>


    //SuperAdmin
    @GET("api/tampildatakec")
    fun getDataKecamatan(): Call<ResponseJSON>
    //SPPT
    @FormUrlEncoded
    @POST("api/totspptkec2")
    fun totalSPPTSuperAdmin(
        @Field("kec")kel:String
    ): Call<ResponseJSON>

    @FormUrlEncoded
    @POST("api/jmlspptbayar")
    fun jumlahSPPTSuperAdmin(
        @Field("kec")kel:String
    ): Call<ResponseJSON>

    @FormUrlEncoded
    @POST("api/totspptbayar")
    fun totalSPPTSuperAdminBayar(
        @Field("kec")kel:String
    ): Call<ResponseJSON>

    @FormUrlEncoded
    @POST("api/jmlpemspptbayar")
    fun jumlahSPPTSuperAdminBayar(
        @Field("kec")kel:String
    ): Call<ResponseJSON>
    //WP
    @FormUrlEncoded
    @POST("api/totalwpjml")
    fun jumlahWPSuperAdmin(
        @Field("kec")kel:String
    ): Call<ResponseJSON>

    @FormUrlEncoded
    @POST("api/totalwp2")
    fun totalWPSuperAdmin(
        @Field("kec")kel:String
    ): Call<ResponseJSON>

    @FormUrlEncoded
    @POST("api/totalwpbayar2")
    fun totalWPBayarSuperAdmin(
        @Field("kec")kel:String
    ): Call<ResponseJSON>

    @FormUrlEncoded
    @POST("api/totalwpbayarjml")
    fun jumlahWPBayarSuperAdmin(
        @Field("kec")kel:String
    ): Call<ResponseJSON>

}