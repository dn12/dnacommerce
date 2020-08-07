package net.adiwilaga.githubuserfinder.vm

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import net.adiwilaga.githubuserfinder.data.dataobject.BaseResponse
import net.adiwilaga.githubuserfinder.repo.DataListListener
import net.adiwilaga.githubuserfinder.repo.DataRepository
import net.adiwilaga.githubuserfinder.repo.PurchaseRepository
import net.adiwilaga.minicommerce.data.dataobject.Category
import net.adiwilaga.minicommerce.data.dataobject.Data
import net.adiwilaga.minicommerce.data.dataobject.Product

class ProductDetailViewModel : ViewModel() {

    var saved:MutableLiveData<Boolean> = MutableLiveData(false)



    fun buy(p:Product,c:Context){
        saved.value=PurchaseRepository.buy(p,c)
    }





}