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
import net.adiwilaga.githubuserfinder.ui.adapter.ProductMiniListAdapter
import net.adiwilaga.minicommerce.R
import net.adiwilaga.minicommerce.data.dataobject.Product
import net.adiwilaga.minicommerce.ui.activity.MainActivity
import net.adiwilaga.minicommerce.ui.activity.ProductDetailActivity


class ProfileFragment : Fragment() {

    lateinit var adp: ProductMiniListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view=inflater.inflate(R.layout.fragment_profile, container, false)

        adp= ProductMiniListAdapter((activity as MainActivity).viewModel.productspurcashed.value!!,(activity as MainActivity), object : ProductListListener {
            override fun OnItemClicked(prod: Product) {
                var ii= Intent(context,ProductDetailActivity::class.java)
                ii.putExtra("product",prod)
                ii.putExtra("purchased",true)
                (activity as MainActivity).startActivity(ii)
            }
        })
        (activity as MainActivity).viewModel.productspurcashed.observe((activity as MainActivity), Observer {
            adp.items=it
            adp.notifyDataSetChanged()
        })
        var prlm= LinearLayoutManager(activity!!)
        prlm.orientation= LinearLayoutManager.VERTICAL
        var rv_product=view.findViewById<RecyclerView>(R.id.rv_product)
        rv_product.layoutManager=prlm
        rv_product.adapter=adp

        (activity as MainActivity).viewModel.getPurchased(activity!!)

        return view
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            ProfileFragment().apply {

            }
    }
}