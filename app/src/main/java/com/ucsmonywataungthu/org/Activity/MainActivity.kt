package com.ucsmonywataungthu.org.Activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.navigation.NavigationView
import com.ucsmonywataungthu.org.R
import com.ucsmonywataungthu.org.fragment.HomeFargment
import com.ucsmonywataungthu.org.fragment.MediaFragment
import com.ucsmonywataungthu.org.fragment.NewsFragment
import com.ucsmonywataungthu.org.fragment.NofiticationFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_trade_center.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    lateinit var sharePreferences: SharedPreferences

    private lateinit var textMessage: TextView
    var fragment: Fragment? = null

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

        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        val nav:NavigationView=findViewById(R.id.bd_nav)
        nav.setNavigationItemSelectedListener(this)
        val transition = supportFragmentManager.beginTransaction()
        transition.replace(R.id.frameLayout, HomeFargment()).commit()
        transition.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

   }

    override fun onNavigationItemSelected(menu: MenuItem): Boolean {
        when (menu.itemId) {

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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_view, menu)

        val item = menu!!.findItem(R.id.action_search)
        search_main.setMenuItem(item)

        return super.onCreateOptionsMenu(menu)
    }
}
