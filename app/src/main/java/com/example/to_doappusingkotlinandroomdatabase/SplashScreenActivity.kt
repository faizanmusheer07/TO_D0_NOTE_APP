package com.example.to_doappusingkotlinandroomdatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import com.example.to_doappusingkotlinandroomdatabase.database.NoteDatabase
import com.example.to_doappusingkotlinandroomdatabase.databinding.ActivitySplashScreenBinding
import com.example.to_doappusingkotlinandroomdatabase.model.CardInfo
import com.example.to_doappusingkotlinandroomdatabase.model.DataObject
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SplashScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashScreenBinding
    private lateinit var myDatabase: NoteDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        myDatabase =
            Room.databaseBuilder(applicationContext, NoteDatabase::class.java, "Database_name")
                .build()
        GlobalScope.launch {
            DataObject.listdata = myDatabase.dbDao().getAllTask() as MutableList<CardInfo>
        }
    }
}