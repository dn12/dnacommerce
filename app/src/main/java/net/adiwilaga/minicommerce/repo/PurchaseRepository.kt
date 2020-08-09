package net.adiwilaga.minicommerce.repo

import android.content.Context
import android.preference.PreferenceManager
import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonObject
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import net.adiwilaga.minicommerce.api.APIBuilder
import net.adiwilaga.minicommerce.data.dataobject.Category
import net.adiwilaga.minicommerce.data.dataobject.Data
import net.adiwilaga.minicommerce.data.dataobject.Product
import retrofit2.HttpException
import java.io.IOException
import java.nio.charset.Charset

object PurchaseRepository  {
    private const val TAG = "PurchaseRepository"

    fun GetPurchased(c:Context):List<Product> {

        var str= getPreverence(c,"purchased")
        Log.e(TAG, "GetPurchased: ".plus(str) )
        var data=Data(ArrayList(),ArrayList())
        if(!str.isNullOrEmpty())
         data= Gson().fromJson<Data>(str,Data::class.java)


        return data.productPromo
    }

    fun buy(p: Product,c: Context):Boolean{
        try {
            var lastdata:MutableList<Product> = ArrayList()
            lastdata.addAll(GetPurchased(c))
            lastdata.add(p)

            var data=Data(ArrayList(),lastdata)
            savePreverence(c,"purchased",Gson().toJson(data))
            return true
        } catch (e: Exception) {
            return false
        }
    }
    fun savePreverence(ctx: Context, key: String, value: String) {
        val prefs = PreferenceManager.getDefaultSharedPreferences(ctx)
        prefs.edit().putString(key, value).apply()
    }

    fun getPreverence(ctx: Context, key: String): String {



        val prefs = PreferenceManager.getDefaultSharedPreferences(ctx)
        return prefs.getString(key, "")!!

    }

}