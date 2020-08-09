package net.adiwilaga.minicommerce.vm

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import net.adiwilaga.minicommerce.repo.DataRepository
import net.adiwilaga.minicommerce.repo.PurchaseRepository


class ProductDetailViewModelFactory(val repo:PurchaseRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ProductDetailViewModel(repo) as T
    }

}