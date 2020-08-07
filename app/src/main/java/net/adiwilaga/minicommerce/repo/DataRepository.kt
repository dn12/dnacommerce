package net.adiwilaga.githubuserfinder.repo

import com.google.gson.Gson
import com.google.gson.JsonObject
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import net.adiwilaga.githubuserfinder.api.APIBuilder
import retrofit2.HttpException
import java.io.IOException
import java.nio.charset.Charset

object DataRepository  {

    var job:CompletableJob? =null

    fun GetData(listener:DataListListener) {
        cancelJob()
        job = Job()
        job?.let { myjob->
            CoroutineScope(IO +myjob).launch {


                try {
                    var data=APIBuilder.apiServices.getData()

                    withContext(Main){
                        if(data.size>0)
                            listener.onSuccess(data.get(0))
                        else
                            listener.onError("No Data")
                    }
                } catch (throwable: Throwable) {
                    when (throwable) {
                        is IOException -> {
                            withContext(Main){
                            listener.onError("Connection Problem")
                        }}
                        is HttpException -> {

                            withContext(Main){
                                listener.onError(convertErrorBody(throwable))
                            }
                        }
                        else -> {
                            withContext(Main){
                                listener.onError(throwable.message.toString())
                            }
                        }
                    }
                }

                myjob.complete()

            }
        }


    }

    fun cancelJob(){
        job?.cancel()
    }


    private fun convertErrorBody(throwable: HttpException):String {
        return try {
            throwable.response()?.errorBody()?.source()?.let {
                it.readByteString().string(Charset.defaultCharset())
            }.toString()
        } catch (exception: Exception) {
            exception.message.toString()
        }
    }
}