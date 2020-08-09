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
import net.adiwilaga.minicommerce.repo.PurchaseRepository
import net.adiwilaga.minicommerce.vm.MainViewModel
import net.adiwilaga.minicommerce.vm.ProductDetailViewModel
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class ProductDetailTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    val ctx=Mockito.mock(Context::class.java)
    val repo = Mockito.mock(PurchaseRepository::class.java)
    val vm = ProductDetailViewModel(repo)

    val p=Product("d","i","http://",0,"$100","t")
    @Test
    fun testBuySuccess() {
        Mockito.`when`(repo.buy(p,ctx))
            .thenReturn(true)

        vm.buy(p,ctx)

        assert(vm.saved.value==true)

    }

    @Test
    fun testBuyFailed() {
        Mockito.`when`(repo.buy(p,ctx))
            .thenReturn(false)

        vm.buy(p,ctx)

        assert(vm.saved.value==false)

    }


}

