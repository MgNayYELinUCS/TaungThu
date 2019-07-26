package com.ucsmonywataungthu.org.Activity

import android.os.Bundle
import android.view.Menu
import android.widget.TextView
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.ucsmonywataungthu.org.R
import com.ucsmonywataungthu.org.fragment.HomeFargment
import com.ucsmonywataungthu.org.fragment.MediaFragment
import com.ucsmonywataungthu.org.fragment.NewsFragment
import com.ucsmonywataungthu.org.fragment.NofiticationFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_trade_center.*

class MainActivity : AppCompatActivity() {
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
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val transition = supportFragmentManager.beginTransaction()
        transition.replace(R.id.frameLayout, HomeFargment()).commit()
        transition.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

   }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_view, menu)

        val item = menu!!.findItem(R.id.action_search)
        search_main.setMenuItem(item)

        return super.onCreateOptionsMenu(menu)
    }
}
