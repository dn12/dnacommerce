package net.adiwilaga.githubuserfinder.api


import net.adiwilaga.githubuserfinder.data.dataobject.BaseResponse
import net.adiwilaga.minicommerce.data.dataobject.Data
import retrofit2.http.GET
import retrofit2.http.Query


interface API {

    @GET("/home")
    suspend fun getData(
    ): List<BaseResponse>



}