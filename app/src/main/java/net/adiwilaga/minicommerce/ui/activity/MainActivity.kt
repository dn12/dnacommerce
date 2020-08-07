package net.adiwilaga.minicommerce.ui.activity

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*
import net.adiwilaga.githubuserfinder.vm.MainViewModel
import net.adiwilaga.minicommerce.R
import net.adiwilaga.minicommerce.ui.fragment.CartFragment
import net.adiwilaga.minicommerce.ui.fragment.FeedFragment
import net.adiwilaga.minicommerce.ui.fragment.HomeFragment
import net.adiwilaga.minicommerce.ui.fragment.ProfileFragment


class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"

    lateinit var viewModel:MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        viewModel.GetData()



        if(intent.getBooleanExtra("topurchased",false)) {
            openFragment(ProfileFragment.newInstance())
            bottom_navigation.selectedItemId=R.id.navigation_profile
        }else
            openFragment(HomeFragment.newInstance())




        bottom_navigation.setOnNavigationItemSelectedListener { item: MenuItem ->
            when (item.getItemId()) {
                 R.id. navigation_home -> {
                     openFragment(HomeFragment.newInstance())
                     true
                }
                R.id.navigation_feed->{
                    openFragment(FeedFragment.newInstance("", ""))
                    true
                }

                R.id.navigation_cart->{
                    openFragment(CartFragment.newInstance("", ""))
                    true
                }
                R.id.navigation_profile->{
                    openFragment(ProfileFragment.newInstance())
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
    fun openFragment(fragment: Fragment?) {
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment!!)
//        transaction.addToBackStack(null)
        transaction.commit()
    }
}