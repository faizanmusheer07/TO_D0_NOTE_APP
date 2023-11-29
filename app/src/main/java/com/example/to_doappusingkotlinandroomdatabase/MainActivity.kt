package com.example.to_doappusingkotlinandroomdatabase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.to_doappusingkotlinandroomdatabase.adapter.TOdoAdapter
import com.example.to_doappusingkotlinandroomdatabase.database.NoteDatabase
import com.example.to_doappusingkotlinandroomdatabase.databinding.ActivityMainBinding
import com.example.to_doappusingkotlinandroomdatabase.model.DataObject
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var myDatabase: NoteDatabase
//    private lateinit var mainAdapter: TOdoAdapter
//    private lateinit var dataList: ArrayList<CardInfo>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =  ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        dataList = ArrayList()



        binding.add.setOnClickListener {
            val intent = Intent(this@MainActivity, CreateCardActivity::class.java)
            startActivity(intent)
        }
    binding.deleteAll.setOnClickListener {
        DataObject.deleteAll()
        GlobalScope.launch {
            myDatabase.dbDao().deleteAll()
        }

        setRecycler()
    }

    setRecycler()



    }
    fun setRecycler(){
        binding.recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
//            mainAdapter= TOdoAdapter(this@MainActivity, dataList)
        binding.recyclerView.adapter = TOdoAdapter(DataObject.getAllData())
    }


}