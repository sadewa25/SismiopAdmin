package dev.sadewawicak.creativesolusindo.sismiopadmin.client

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class APIResponse {

    val url:String = "http://223.25.98.84:81/epbb/"
    fun response():APIClient{
        val client = OkHttpClient.Builder()
            .connectTimeout(2400, TimeUnit.SECONDS)
            .readTimeout(2400, TimeUnit.SECONDS).build()
        val retrofit: Retrofit = Retrofit.Builder().baseUrl(url)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(APIClient::class.java)
    }

}