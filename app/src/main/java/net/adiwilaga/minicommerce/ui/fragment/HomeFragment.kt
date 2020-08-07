package net.adiwilaga.minicommerce.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_home.*
import net.adiwilaga.githubuserfinder.ui.adapter.CategoryListAdapter
import net.adiwilaga.githubuserfinder.ui.adapter.ProductListAdapter
import net.adiwilaga.githubuserfinder.vm.DataViewModel
import net.adiwilaga.minicommerce.R
import net.adiwilaga.minicommerce.data.dataobject.Category
import net.adiwilaga.minicommerce.data.dataobject.Product
import net.adiwilaga.minicommerce.ui.activity.MainActivity


class HomeFragment : Fragment() {
    private val TAG = "HomeFragment"


    var products:List<Product> = ArrayList()
    var category:List<Category> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view=inflater.inflate(R.layout.fragment_home, container, false)




        var catlm=LinearLayoutManager(activity!!)
        catlm.orientation=LinearLayoutManager.HORIZONTAL
        var rv_category=view.findViewById<RecyclerView>(R.id.rv_category)
        rv_category.layoutManager=catlm
        rv_category.adapter=(activity as MainActivity).adp


        var prlm=LinearLayoutManager(activity!!)
        prlm.orientation=LinearLayoutManager.VERTICAL
        var rv_product=view.findViewById<RecyclerView>(R.id.rv_product)
        rv_product.layoutManager=prlm
        rv_product.adapter=(activity as MainActivity).adp1



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