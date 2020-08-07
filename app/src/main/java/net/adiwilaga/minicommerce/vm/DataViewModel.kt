package net.adiwilaga.githubuserfinder.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import net.adiwilaga.githubuserfinder.data.dataobject.BaseResponse
import net.adiwilaga.githubuserfinder.repo.DataListListener
import net.adiwilaga.githubuserfinder.repo.DataRepository
import net.adiwilaga.minicommerce.data.dataobject.Category
import net.adiwilaga.minicommerce.data.dataobject.Data
import net.adiwilaga.minicommerce.data.dataobject.Product

class DataViewModel : ViewModel() {

    var isloading:MutableLiveData<Boolean> = MutableLiveData(false)
    var errormessage:MutableLiveData<String> = MutableLiveData("")


    var category:MutableLiveData<List<Category>> = MutableLiveData(ArrayList())
    var products:MutableLiveData<List<Product>> = MutableLiveData(ArrayList())


    fun GetData(){
        isloading.value=true
        DataRepository.GetData(object : DataListListener {
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





}