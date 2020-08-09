package net.adiwilaga.minicommerce.data.dataobject


import com.google.gson.annotations.SerializedName
import net.adiwilaga.minicommerce.data.dataobject.Data

data class BaseResponse(
    @SerializedName("data")
    val data: Data

)