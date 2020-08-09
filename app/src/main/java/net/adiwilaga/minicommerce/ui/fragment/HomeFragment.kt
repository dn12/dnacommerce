package net.adiwilaga.minicommerce.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_home.*
import net.adiwilaga.minicommerce.ui.adapter.CategoryListAdapter
import net.adiwilaga.minicommerce.ui.adapter.ProductListAdapter
import net.adiwilaga.minicommerce.ui.adapter.ProductListListener
import net.adiwilaga.minicommerce.R
import net.adiwilaga.minicommerce.data.dataobject.Category
import net.adiwilaga.minicommerce.data.dataobject.Product
import net.adiwilaga.minicommerce.ui.activity.MainActivity
import net.adiwilaga.minicommerce.ui.activity.ProductDetailActivity
import net.adiwilaga.minicommerce.ui.activity.SearchActivity


class HomeFragment : BaseFragment() {
    private val TAG = "HomeFragment"



    lateinit var adp:CategoryListAdapter
    lateinit var adp1: ProductListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Title="HOME"

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

        var rlsearch=view.findViewById<RelativeLayout>(R.id.rlsearch)
        rlsearch.setOnClickListener {
            var ii= Intent(context,SearchActivity::class.java)
            ii.putExtra("product", bundleOf(Pair("product",adp1.items)))
            (activity as MainActivity).startActivity(ii)
        }

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