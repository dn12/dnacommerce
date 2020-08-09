package net.adiwilaga.minicommerce.repo

import net.adiwilaga.minicommerce.data.dataobject.BaseResponse
import net.adiwilaga.minicommerce.data.dataobject.Data

interface DataListListener {

    fun onSuccess(users:BaseResponse)
    fun onError(err:String)

}