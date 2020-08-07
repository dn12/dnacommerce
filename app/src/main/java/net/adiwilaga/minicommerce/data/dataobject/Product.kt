package net.adiwilaga.minicommerce.data.dataobject


import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("description")
    val description: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("imageUrl")
    val imageUrl: String,
    @SerializedName("loved")
    var loved: Int,
    @SerializedName("price")
    val price: String,
    @SerializedName("title")
    val title: String
)