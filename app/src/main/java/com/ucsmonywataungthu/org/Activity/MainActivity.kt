package com.ucsmonywataungthu.org.Activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.navigation.NavigationView
import com.ucsmonywataungthu.org.DrawerActivity.ContactUsActivity
import com.ucsmonywataungthu.org.DrawerActivity.ProfileInformationActivity
import com.ucsmonywataungthu.org.Network.APIInitiate
import com.ucsmonywataungthu.org.Network.APIService
import com.ucsmonywataungthu.org.R
import com.ucsmonywataungthu.org.fragment.HomeFargment
import com.ucsmonywataungthu.org.fragment.MediaFragment
import com.ucsmonywataungthu.org.fragment.NewsFragment
import com.ucsmonywataungthu.org.fragment.NofiticationFragment
import com.ucsmonywataungthu.org.model.Merchant
import com.ucsmonywataungthu.org.model.MerchantModel
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    lateinit var sharePreferences: SharedPreferences
    lateinit var apiService: APIService

    private lateinit var textMessage: TextView
    var fragment: Fragment? = null
    lateinit var merchantModel:List<MerchantModel>
    lateinit var nav:NavigationView
    var user_id:Int = 0
lateinit var navMenu:Menu
    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                fragment=HomeFargment()
                //return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_media -> {
                fragment=MediaFragment()
                //return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_news ->{
                fragment=NewsFragment()
            }
            R.id.navigation_notifications -> {
                fragment=NofiticationFragment()
                //return@OnNavigationItemSelectedListener true
            }

        }
        val transition = supportFragmentManager.beginTransaction()
        transition.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        transition.replace(R.id.frameLayout, fragment!!).commit()
        true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        toolbar.setTitle("Taung Thu")
        sharePreferences=getSharedPreferences("MyPref", Context.MODE_PRIVATE)
        val role=sharePreferences.getInt("role",0)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        nav=findViewById(R.id.bd_nav)
        nav.setNavigationItemSelectedListener(this)
        navMenu=nav.menu

        /*if (role==0){
            navMenu.findItem(R.id.nav1).setVisible(true)
            navMenu.findItem(R.id.nav2).setVisible(false)
        }else if (role==1){
            navMenu.findItem(R.id.nav1).setVisible(false)
            navMenu.findItem(R.id.nav2).setVisible(true)
        }*/
       user_id=sharePreferences.getInt("user_id",0)
        apiService= APIInitiate.client.create((APIService::class.java))
        Log.i("userid",user_id.toString())
        apiService.getMerchantByUser(user_id).enqueue(object : Callback<Merchant>{
            override fun onFailure(call: Call<Merchant>, t: Throwable) {
                Toast.makeText(applicationContext,"error",Toast.LENGTH_SHORT).show()
                Log.i("error",t.toString())

            }

            override fun onResponse(call: Call<Merchant>, response: Response<Merchant>) {

                if (response.isSuccessful){
                    if (!response.body()!!.merchant.isEmpty()) {
                         merchantModel= response.body()!!.merchant
                        if (merchantModel[0]!!.status==1){

                        }

                        navMenu.findItem(R.id.nav1).setVisible(false)
                        navMenu.findItem(R.id.nav2).setVisible(true)
                    }
                    else{

                        navMenu.findItem(R.id.nav1).setVisible(true)
                        navMenu.findItem(R.id.nav2).setVisible(false)

                    }

                }
            }
        })
        val transition = supportFragmentManager.beginTransaction()
        transition.replace(R.id.frameLayout, HomeFargment()).commit()
        transition.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

   }

    override fun onNavigationItemSelected(menu: MenuItem): Boolean {
        when (menu.itemId) {
            R.id.nav1->{
                startActivity(Intent(applicationContext,MerchantRegisterActivity::class.java))
            }
            R.id.nav2->{

                var intent=Intent(applicationContext,MerchantProfileActivity::class.java)
                /*intent.putExtra("status",merchantModel[0]!!.status)
                intent.putExtra("merchant_id",merchantModel[0]!!.id)
*/
                startActivity(intent)
            }
            R.id.nav3->{
                startActivity(Intent(applicationContext,ContactUsActivity::class.java))
            }

            R.id.nav4 -> {
                sharePreferences.edit().clear().commit()

                //start login
                startActivity(Intent(this, LoginActivity::class.java))
                finish()

                Toast.makeText(this, "Logout", Toast.LENGTH_LONG).show()
                true


            }
        }
        return true
    }

   /* override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_view, menu)

        val item = menu!!.findItem(R.id.action_search)
        search_main.setMenuItem(item)

        return super.onCreateOptionsMenu(menu)
    }*/
}
