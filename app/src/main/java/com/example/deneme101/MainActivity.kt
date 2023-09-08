package com.example.deneme101

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.PopupMenu
import androidx.appcompat.widget.SearchView//eski olan import android.widget.SearchView
import android.widget.SearchView.OnQueryTextListener
import android.widget.Toast

import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    private lateinit var itemList: ArrayList<ExampleItem>
    private lateinit var adapter: ExampleAdapter
    //private lateinit var searchView: SearchView
    private lateinit var searchView: androidx.appcompat.widget.SearchView

    private lateinit var vt: VeriTabaniH
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val siralaButton: ImageButton
        siralaButton = findViewById(R.id.siralaButton)
        supportActionBar?.hide()

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)
        vt = VeriTabaniH(this)
        itemList = Notlardao().tumNotlar(vt)

        adapter = ExampleAdapter(this, itemList)
        recyclerView.adapter = adapter

        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this, NoteGirisActivity::class.java)
            startActivity(intent)
        }

        siralaButton.setOnClickListener { view ->
            showFilterPopup(view)
        }
        searchView = findViewById(R.id.searchView)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText)
                return true
            }

        })
      }
    private fun filterList(query:String?){
        if (query!=null){
            val filteredList=ArrayList<ExampleItem>()
            for(i in itemList){
                if (i.text1.toLowerCase(Locale.ROOT).contains(query)){
                    filteredList.add(i)
                }
            }
            if(filteredList.isEmpty()){
                Toast.makeText(this,"Not Bulunamadı.",Toast.LENGTH_SHORT).show()
            }else{
                adapter.setFilteredList(filteredList)
            }
        }
    }


    private fun showFilterPopup(view: View) {
        val popupMenu = PopupMenu(this, view)
        popupMenu.menuInflater.inflate(R.menu.siralama, popupMenu.menu)

        // Set a listener for popup menu item clicks
        popupMenu.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.sort_a_to_z -> {

                    true
                }
                R.id.sort_z_to_a -> {

                    true
                }
                else -> false
            }
        }

        popupMenu.show()
    }

    override fun onBackPressed() {
        val intent = Intent(Intent.ACTION_MAIN)
        intent.addCategory(Intent.CATEGORY_HOME)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }


}