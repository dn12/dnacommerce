package net.adiwilaga.minicommerce.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_search.*
import net.adiwilaga.minicommerce.ui.adapter.ProductListAdapter
import net.adiwilaga.minicommerce.ui.adapter.ProductListListener
import net.adiwilaga.minicommerce.ui.adapter.ProductMiniListAdapter
import net.adiwilaga.minicommerce.vm.MainViewModel
import net.adiwilaga.minicommerce.vm.SearchViewModel
import net.adiwilaga.minicommerce.R
import net.adiwilaga.minicommerce.data.dataobject.Product

class SearchActivity : AppCompatActivity() {
    private  val TAG = "SearchActivity"


    lateinit var viewModel:SearchViewModel
    lateinit var adp: ProductMiniListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        viewModel = ViewModelProvider(this).get(SearchViewModel::class.java)
        var bundle=intent.getBundleExtra("product")
        var lsproduct= bundle.get("product") as List<Product>

        viewModel.setProducts(lsproduct)

        adp= ProductMiniListAdapter(viewModel.products.value!!,this, object : ProductListListener {
            override fun OnItemClicked(prod: Product) {
                var ii= Intent(this@SearchActivity,ProductDetailActivity::class.java)
                ii.putExtra("product",prod)
                startActivity(ii)
            }
        })
        viewModel.products.observe(this, Observer {
            adp.items=it
            adp.notifyDataSetChanged()
        })
        var prlm= LinearLayoutManager(this)
        prlm.orientation= LinearLayoutManager.VERTICAL
        rv_product.layoutManager=prlm
        rv_product.adapter=adp

        etsearch.addTextChangedListener(object:TextWatcher{
            override fun afterTextChanged(p0: Editable?) {
                viewModel.search(p0.toString())
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })


    }
}