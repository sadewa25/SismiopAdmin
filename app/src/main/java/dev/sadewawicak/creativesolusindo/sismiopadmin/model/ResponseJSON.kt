package dev.sadewawicak.creativesolusindo.sismiopadmin.model


import com.google.gson.annotations.SerializedName

data class ResponseJSON(

    @field:SerializedName("result")
	val result: List<DataModel?>? = null,

    @field:SerializedName("value")
	val value: Int? = null
)