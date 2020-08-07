package net.adiwilaga.githubuserfinder.repo

import net.adiwilaga.githubuserfinder.data.dataobject.BaseResponse
import net.adiwilaga.minicommerce.data.dataobject.Data

interface DataListListener {

    fun onSuccess(users:BaseResponse)
    fun onError(err:String)

}