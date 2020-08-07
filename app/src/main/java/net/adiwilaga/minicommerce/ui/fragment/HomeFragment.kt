package net.adiwilaga.minicommerce.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import net.adiwilaga.githubuserfinder.ui.adapter.CategoryListAdapter
import net.adiwilaga.githubuserfinder.ui.adapter.ProductListAdapter
import net.adiwilaga.githubuserfinder.ui.adapter.ProductListListener
import net.adiwilaga.minicommerce.R
import net.adiwilaga.minicommerce.data.dataobject.Category
import net.adiwilaga.minicommerce.data.dataobject.Product
import net.adiwilaga.minicommerce.ui.activity.MainActivity
import net.adiwilaga.minicommerce.ui.activity.ProductDetailActivity


class HomeFragment : Fragment() {
    private val TAG = "HomeFragment"


    var products:List<Product> = ArrayList()
    var category:List<Category> = ArrayList()

    lateinit var adp:CategoryListAdapter
    lateinit var adp1: ProductListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view=inflater.inflate(R.layout.fragment_home, container, false)

        adp= CategoryListAdapter((activity as MainActivity).viewModel.category.value!!,(activity as MainActivity))
        adp1= ProductListAdapter((activity as MainActivity).viewModel.products.value!!,(activity as MainActivity), object : ProductListListener {
            override fun OnItemClicked(prod: Product) {
                var ii= Intent(context,ProductDetailActivity::class.java)
                ii.putExtra("product",prod)
                (activity as MainActivity).startActivity(ii)
            }
        })
        (activity as MainActivity).viewModel.products.observe((activity as MainActivity), Observer {
            Log.e(TAG, "data changed".plus(it.size) )
            adp1.items=it
            adp1.notifyDataSetChanged()
        })
        (activity as MainActivity).viewModel.category.observe((activity as MainActivity), Observer {
            adp.items=it
            adp.notifyDataSetChanged()
        })


        var catlm=LinearLayoutManager(activity!!)
        catlm.orientation=LinearLayoutManager.HORIZONTAL
        var rv_category=view.findViewById<RecyclerView>(R.id.rv_category)
        rv_category.layoutManager=catlm
        rv_category.adapter=adp


        var prlm=LinearLayoutManager(activity!!)
        prlm.orientation=LinearLayoutManager.VERTICAL
        var rv_product=view.findViewById<RecyclerView>(R.id.rv_product)
        rv_product.layoutManager=prlm
        rv_product.adapter=adp1



        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    companion object {

        @JvmStatic
        fun newInstance() = HomeFragment()
    }
}