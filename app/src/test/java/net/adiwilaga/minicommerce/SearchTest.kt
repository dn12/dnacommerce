package net.adiwilaga.minicommerce

import android.content.Context
import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.any
import net.adiwilaga.minicommerce.data.dataobject.BaseResponse
import net.adiwilaga.minicommerce.data.dataobject.Category
import net.adiwilaga.minicommerce.data.dataobject.Data
import net.adiwilaga.minicommerce.data.dataobject.Product
import net.adiwilaga.minicommerce.repo.DataListListener
import net.adiwilaga.minicommerce.repo.DataRepository
import net.adiwilaga.minicommerce.vm.MainViewModel
import net.adiwilaga.minicommerce.vm.SearchViewModel
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class SearchTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    val vm = SearchViewModel()

    val p=Product("d","i","http://",0,"$100","t")
    @Test
    fun testDataSet() {

        vm.setProducts(listOf(p))


        assert(vm.masterproducts.value?.size == 1)
    }

    @Test
    fun testEmptyDataSet() {
        vm.setProducts(listOf())
        assert(vm.masterproducts.value?.size == 0)
    }

    @Test
    fun tesSearchAvailable() {
        vm.setProducts(listOf(p))
        vm.search("t")

        assert(vm.products.value?.size==1)

    }

    @Test
    fun tesSearchEmpty() {
        vm.setProducts(listOf(p))
        vm.search("")

        assert(vm.products.value?.size==0)

    }
}

