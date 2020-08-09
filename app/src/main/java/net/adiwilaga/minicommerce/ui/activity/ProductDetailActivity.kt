package net.adiwilaga.minicommerce.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_product_detail.*
import net.adiwilaga.minicommerce.vm.ProductDetailViewModel
import net.adiwilaga.minicommerce.R
import net.adiwilaga.minicommerce.data.dataobject.Product
import net.adiwilaga.minicommerce.repo.DataRepository
import net.adiwilaga.minicommerce.repo.PurchaseRepository
import net.adiwilaga.minicommerce.vm.MainViewModel
import net.adiwilaga.minicommerce.vm.MainViewModelFactory
import net.adiwilaga.minicommerce.vm.ProductDetailViewModelFactory

class ProductDetailActivity : AppCompatActivity() {

    private lateinit var viewModel: ProductDetailViewModel
    lateinit var mproduct:Product
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        viewModel = ViewModelProvider(this,
            ProductDetailViewModelFactory(PurchaseRepository)
        ).get(ProductDetailViewModel::class.java)

        mproduct=intent.getSerializableExtra("product") as Product

        Glide.with(this).load(mproduct.imageUrl).into(imgava)
        tname.setText(mproduct.title)
        tdes.setText(mproduct.description)
        tprice.setText(mproduct.price)
        image_like.setOnClickListener{
            if(mproduct.loved==1)
                mproduct.loved=0
            else
                mproduct.loved=1

            setlove(mproduct.loved, it as ImageView)
        }
        setlove(mproduct.loved,image_like)

        image_share.setOnClickListener {
            try {

                val share = Intent(Intent.ACTION_SEND)
                share.type = "text/plain"
                share.putExtra(Intent.EXTRA_TEXT, mproduct.title.plus("->").plus(mproduct.imageUrl))
                startActivity(Intent.createChooser(share, "Share Link"))
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        image_back.setOnClickListener {
            onBackPressed()
        }
        b_buy.setOnClickListener {
            viewModel.buy(mproduct,this)
        }

        viewModel.saved.observe(this, Observer {
            if(it) {
                var ii= Intent(this,MainActivity::class.java)
                ii.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                ii.putExtra("topurchased",true)
                startActivity(ii)
                finish()
            }
        })



        if(intent.getBooleanExtra("purchased",false)){
            b_buy.visibility= View.GONE
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