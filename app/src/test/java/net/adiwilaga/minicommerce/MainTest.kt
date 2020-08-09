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
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class MainTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    val ctx=Mockito.mock(Context::class.java)
    val repo = Mockito.mock(DataRepository::class.java)
    val purchaserepo = Mockito.mock(PurchaseRepository::class.java)
    val vm = MainViewModel(repo,purchaserepo)

    val p=Product("d","i","http://",0,"$100","t")
    val c=Category(1,"http://","name")
    @Test
    fun testDataList() {
        Mockito.`when`(repo.GetData(any()))
            .thenAnswer {
                (it.arguments.get(0) as DataListListener).onSuccess(BaseResponse(
                    Data(listOf(
                            c
                        ),
                        listOf(
                            p
                        )
                    )
                ))
            }

        vm.GetData()

        assert(vm.products.value?.size == 1)
        assert(vm.category.value?.size == 1)
    }

    @Test
    fun testEmptyDataList() {
        Mockito.`when`(repo.GetData(any()))
            .thenAnswer {
                (it.arguments.get(0) as DataListListener).onSuccess(BaseResponse(
                    Data(listOf(),
                        listOf()
                    )
                ))
            }

        vm.GetData()

        assert(vm.products.value?.size == 0)
        assert(vm.category.value?.size == 0)
    }

    @Test
    fun tesDataError() {
        Mockito.`when`(repo.GetData(any()))
            .thenAnswer {
                (it.arguments.get(0) as DataListListener).onError("err")
            }

        vm.GetData()

        assert(vm.errormessage.value=="err")

    }

    @Test
    fun tesPurchasedList() {
        Mockito.`when`(purchaserepo.GetPurchased(ctx))
            .thenReturn(listOf(p))



        vm.getPurchased(ctx)

        assert(vm.productspurcashed.value?.size==1)

    }

    @Test
    fun tesPurchasedEmptyList() {
        Mockito.`when`(purchaserepo.GetPurchased(ctx))
            .thenReturn(listOf())

        vm.getPurchased(ctx)

        assert(vm.productspurcashed.value?.size==0)

    }
}

