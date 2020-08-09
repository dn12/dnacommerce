package net.adiwilaga.minicommerce.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import net.adiwilaga.minicommerce.R
import net.adiwilaga.minicommerce.data.dataobject.Product

class ProductListAdapter(var items: List<Product>, val context: Context, val lst:ProductListListener) : RecyclerView.Adapter<ProductViewHolder>() {

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(LayoutInflater.from(context).inflate(R.layout.item_product, parent, false))
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {



        var u=items.get(position)
        holder.tname?.text=u.title
        Glide.with(context).load(u.imageUrl).into(holder.img)

        holder.img.setOnClickListener {
            lst.OnItemClicked(u)
        }

        setlove(u.loved,holder.img_like)

        holder.img_like.setOnClickListener{
            if(u.loved==1)
                u.loved=0
            else
                u.loved=1

            setlove(u.loved, it as ImageView)
        }
    }

    fun setlove(love:Int,v:ImageView){
        if(love==1){
            v.setImageResource(R.drawable.icon_like_fill)
        }else{
            v.setImageResource(R.drawable.icon_like_blank)
        }
    }

}
class ProductViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    val img = view.findViewById<ImageView>(R.id.imgava)
    val img_like = view.findViewById<ImageView>(R.id.image_like)
    val tname=view.findViewById<TextView>(R.id.tname)

}

interface ProductListListener{
     fun OnItemClicked(prod:Product)
}
