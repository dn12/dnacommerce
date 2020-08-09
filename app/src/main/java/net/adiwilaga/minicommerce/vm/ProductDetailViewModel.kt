package net.adiwilaga.minicommerce.vm

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import net.adiwilaga.minicommerce.data.dataobject.BaseResponse
import net.adiwilaga.minicommerce.repo.DataListListener
import net.adiwilaga.minicommerce.repo.DataRepository
import net.adiwilaga.minicommerce.repo.PurchaseRepository
import net.adiwilaga.minicommerce.data.dataobject.Category
import net.adiwilaga.minicommerce.data.dataobject.Data
import net.adiwilaga.minicommerce.data.dataobject.Product

class ProductDetailViewModel(val repo:PurchaseRepository) : ViewModel() {

    var saved:MutableLiveData<Boolean> = MutableLiveData(false)



    fun buy(p:Product,c:Context){
        saved.value=repo.buy(p,c)
    }





}