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

class SearchViewModel : ViewModel() {

    var saved:MutableLiveData<Boolean> = MutableLiveData(false)

    var masterproducts:MutableLiveData<List<Product>> = MutableLiveData(ArrayList())
    var products:MutableLiveData<List<Product>> = MutableLiveData(ArrayList())

    fun setProducts(p:List<Product>){
        masterproducts.value=p
    }


    fun search(_q:String){
        var q=_q.toLowerCase()
        var query:MutableList<Product> =ArrayList()
        if(!q.isNullOrEmpty())
        for (p in masterproducts.value!!){
            if (p.title.toLowerCase().contains(q) || p.description.toLowerCase().contains(q))
                query.add(p)
        }
        products.value=query
    }


}