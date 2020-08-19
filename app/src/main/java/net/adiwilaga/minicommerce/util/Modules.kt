package net.adiwilaga.minicommerce.util

import net.adiwilaga.minicommerce.repo.DataRepository
import net.adiwilaga.minicommerce.repo.PurchaseRepository
import net.adiwilaga.minicommerce.vm.MainViewModel
import net.adiwilaga.minicommerce.vm.ProductDetailViewModel
import net.adiwilaga.minicommerce.vm.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule=module{

    single { DataRepository }
    single { PurchaseRepository }
    viewModel { MainViewModel(get(),get()) }
    viewModel { ProductDetailViewModel(get()) }
    viewModel { SearchViewModel() }

}