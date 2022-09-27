package com.example.mvvmapp.api


import com.example.mywords.db.WordyEntity
import retrofit2.Call
import retrofit2.http.*


interface RestApi {
    @Headers("Content-Type: application/json")
    @POST("post")
    fun addWordy(@Body wordyData: WordyEntity): Call<List<WordyEntity>>

    @GET("get")
    fun getWordy():Call<List<WordyEntity>>

    @DELETE("delete/{userId} ")
    fun deleteWordy(@Path("userId") id: Int): Call<List<WordyEntity>>
}
