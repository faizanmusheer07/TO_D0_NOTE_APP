package com.example.to_doappusingkotlinandroomdatabase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import com.example.to_doappusingkotlinandroomdatabase.database.NoteDatabase
import com.example.to_doappusingkotlinandroomdatabase.database.NoteEntity
import com.example.to_doappusingkotlinandroomdatabase.databinding.ActivityCreateCardBinding
import com.example.to_doappusingkotlinandroomdatabase.model.DataObject
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CreateCardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreateCardBinding
    private lateinit var myDatabase: NoteDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateCardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        myDatabase =
            Room.databaseBuilder(applicationContext, NoteDatabase::class.java, "Database_name")
                .build()

        binding.apply {
            saveButton.setOnClickListener {
                if (binding.createTitle.text.toString().trim { it <= ' ' }.isNotEmpty()
                    && binding.createPriority.text.toString().trim { it <= ' ' }.isNotEmpty()
                ) {
                    val title = binding.createTitle.getText().toString()
                    val priority = binding.createPriority.getText().toString()
                    DataObject.setData(title, priority)
                    GlobalScope.launch {
                        myDatabase.dbDao().insert(NoteEntity(0, title, priority))
                    }

                    val intent = Intent(this@CreateCardActivity, MainActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }
}