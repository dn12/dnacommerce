package net.adiwilaga.minicommerce.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import net.adiwilaga.minicommerce.R
import net.adiwilaga.minicommerce.data.dataobject.Product

class ProductMiniListAdapter(var items: List<Product>, val context: Context, val lst:ProductListListener) : RecyclerView.Adapter<ProductMiniViewHolder>() {

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductMiniViewHolder {
        return ProductMiniViewHolder(LayoutInflater.from(context).inflate(R.layout.item_product_mini, parent, false))
    }

    override fun onBindViewHolder(holder: ProductMiniViewHolder, position: Int) {



        var u=items.get(position)
        holder.tname?.text=u.title
        holder.tprice?.text=u.price
        Glide.with(context).load(u.imageUrl).into(holder.img)

        holder.rlmain.setOnClickListener {
            lst.OnItemClicked(u)
        }


    }

}
class ProductMiniViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    val rlmain = view.findViewById<RelativeLayout>(R.id.rlmain)
    val img = view.findViewById<ImageView>(R.id.imgava)
    val tprice = view.findViewById<TextView>(R.id.tprice)
    val tname=view.findViewById<TextView>(R.id.tname)

}


