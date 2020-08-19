package net.adiwilaga.minicommerce.ui.activity

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_main.*
import net.adiwilaga.minicommerce.vm.MainViewModel
import net.adiwilaga.minicommerce.R
import net.adiwilaga.minicommerce.ui.fragment.*
import org.koin.android.ext.android.inject


class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"

     val viewModel by inject<MainViewModel> ()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        viewModel.GetData()



        if(intent.getBooleanExtra("topurchased",false)) {
            openFragment(ProfileFragment.newInstance())
            bottom_navigation.selectedItemId=R.id.navigation_profile
        }else {
            openFragment(HomeFragment.newInstance())
        }



        bottom_navigation.setOnNavigationItemSelectedListener { item: MenuItem ->
            when (item.getItemId()) {
                 R.id. navigation_home -> {
                     openFragment(HomeFragment.newInstance())
                     title="HOME"
                     true
                }
                R.id.navigation_feed->{
                    openFragment(FeedFragment.newInstance("", ""))
                    title="FEED"
                    true
                }

                R.id.navigation_cart->{
                    openFragment(CartFragment.newInstance("", ""))
                    title="CART"
                    true
                }
                R.id.navigation_profile->{
                    openFragment(ProfileFragment.newInstance())
                    title="Profile"
                    true
                }

                else -> false
            }

        }


        viewModel.isloading.observe(this, Observer {
            if(it)
                progressbar.visibility= View.VISIBLE
            else
                progressbar.visibility= View.INVISIBLE
        })

        viewModel.errormessage.observe(this, Observer {
            if(it.length>0){
                Toast.makeText(this,it, Toast.LENGTH_LONG).show()
            }
        })
    }
    fun openFragment(fragment: BaseFragment?) {
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment!!)
//        transaction.addToBackStack(null)
        transaction.commit()
        title=fragment.Title
    }
}