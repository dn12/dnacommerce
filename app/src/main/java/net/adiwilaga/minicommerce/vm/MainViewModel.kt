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

class MainViewModel(val repo:DataRepository,val purchaserepo:PurchaseRepository) : ViewModel() {

    var isloading:MutableLiveData<Boolean> = MutableLiveData(false)
    var errormessage:MutableLiveData<String> = MutableLiveData("")


    var category:MutableLiveData<List<Category>> = MutableLiveData(ArrayList())
    var products:MutableLiveData<List<Product>> = MutableLiveData(ArrayList())
    var productspurcashed:MutableLiveData<List<Product>> = MutableLiveData(ArrayList())


    fun GetData(){
        isloading.value=true
        repo.GetData(object : DataListListener {
            override fun onSuccess(data: BaseResponse) {
                isloading.value=false
                category.value=data.data.category
                products.value=data.data.productPromo

            }

            override fun onError(err: String) {
                isloading.value=false
                errormessage.value=err
            }
        })


    }


    fun getPurchased(c:Context){
        productspurcashed.value=purchaserepo.GetPurchased(c)
    }





}