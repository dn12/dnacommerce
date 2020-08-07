package net.adiwilaga.githubuserfinder.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import net.adiwilaga.minicommerce.R
import net.adiwilaga.minicommerce.data.dataobject.Category

class CategoryListAdapter(var items: List<Category>, val context: Context) : RecyclerView.Adapter<CategoryViewHolder>() {

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(LayoutInflater.from(context).inflate(R.layout.item_category, parent, false))
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {



        val u=items.get(position)
        holder.tname?.text=u.name
        Glide.with(context).load(u.imageUrl).into(holder.img)

    }


}
class CategoryViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    val img = view.findViewById<ImageView>(R.id.imgava)
    val tname=view.findViewById<TextView>(R.id.tname)

}


