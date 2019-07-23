package com.ucsmonywataungthu.org

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ucsmonywataungthu.org.adapter.TradeCenterAdapter
import com.ucsmonywataungthu.org.model.HomeModel
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_trade_center.*
import android.view.Menu
import com.miguelcatalan.materialsearchview.MaterialSearchView
import android.text.TextUtils
import java.nio.file.Files.size
import android.speech.RecognizerIntent
import android.content.Intent








class TradeCenter : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        Toast.makeText(this, " You select >> " + this.options!![position], Toast.LENGTH_SHORT).show(); }

    var options: Array<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trade_center)
       // search_view.setCursorDrawable(R.drawable.cusot)
        setSupportActionBar(toolbar)

        val categoryList = ArrayList<HomeModel>()
        categoryList.add(HomeModel(R.mipmap.home, "ေျမပဲ"))
        categoryList.add(HomeModel(R.mipmap.home, "ေျမပဲ"))
        categoryList.add(HomeModel(R.mipmap.home, "ေျမပဲ"))
        categoryList.add(HomeModel(R.mipmap.home, "ေျမပဲ"))
        categoryList.add(HomeModel(R.mipmap.home, "ေျမပဲ"))


        var mainListRecycler = findViewById<RecyclerView>(R.id.tradecneterrecycle)
        mainListRecycler.layoutManager = GridLayoutManager(this, 3) as RecyclerView.LayoutManager?
        var adapter = TradeCenterAdapter(this!!, categoryList)
        mainListRecycler.adapter = adapter


        val adapter1 = ArrayAdapter.createFromResource(
            this,
            R.array.Animals, android.R.layout.simple_spinner_item
        )
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinner.setAdapter(adapter1);
        spinner.setOnItemSelectedListener(this)
        options = this.getResources().getStringArray(R.array.Animals)

        val searchView = findViewById(R.id.search_view) as MaterialSearchView
        searchView.setOnQueryTextListener(object : MaterialSearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                //Do some magic
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                //Do some magic
                return false
            }
        })

        searchView.setOnSearchViewListener(object : MaterialSearchView.SearchViewListener {
            override fun onSearchViewShown() {
                //Do some magic
            }

            override fun onSearchViewClosed() {
                //Do some magic
            }
        })

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.search_view, menu)

        val item = menu.findItem(R.id.action_search)
        search_view.setMenuItem(item)

        return true
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == MaterialSearchView.REQUEST_VOICE && resultCode == Activity.RESULT_OK) {
            val matches = data!!.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
            if (matches != null && matches.size > 0) {
                val searchWrd = matches[0]
                if (!TextUtils.isEmpty(searchWrd)) {
                    search_view.setQuery(searchWrd, false)
                }
            }

            return
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
    override fun onBackPressed() {
        if (search_view.isSearchOpen()) {
            search_view.closeSearch()
        } else {
            super.onBackPressed()
        }
    }


}