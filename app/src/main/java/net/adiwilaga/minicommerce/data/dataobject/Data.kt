package net.adiwilaga.minicommerce.data.dataobject


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("category")
    val category: List<Category>,
    @SerializedName("productPromo")
    val productPromo: List<Product>
)