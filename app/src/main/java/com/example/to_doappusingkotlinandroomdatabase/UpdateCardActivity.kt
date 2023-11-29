package com.example.to_doappusingkotlinandroomdatabase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.room.Entity
import androidx.room.Room
import com.example.to_doappusingkotlinandroomdatabase.database.NoteDatabase
import com.example.to_doappusingkotlinandroomdatabase.database.NoteEntity
import com.example.to_doappusingkotlinandroomdatabase.databinding.ActivityUpdateCardBinding
import com.example.to_doappusingkotlinandroomdatabase.model.DataObject
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class UpdateCardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUpdateCardBinding
    private lateinit var myDatabase: NoteDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateCardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        myDatabase =
            Room.databaseBuilder(applicationContext, NoteDatabase::class.java, "Database_name")
                .build()


        val position = intent.getIntExtra("id", 0)
        if (position == 0) {
            val title = DataObject.getData(position).title
            val priority = DataObject.getData(position).priority
            binding.createTitle.setText(title)
            binding.createPriority.setText(priority)

            binding.deleteButton.setOnClickListener {
                DataObject.deleteData(position)
                GlobalScope.launch {
                    myDatabase.dbDao().delete(
                        NoteEntity(
                            position + 1,
                            binding.createTitle.text.toString(),
                            binding.createTitle.text.toString()
                        )
                    )
                }
                myIntent()
            }
            binding.updateButton.setOnClickListener {
                DataObject.updateData(
                    position,
                    binding.createTitle.text.toString(),
                    binding.createPriority.text.toString()
                )
                Toast.makeText(this@UpdateCardActivity, title + "" + priority, Toast.LENGTH_SHORT)
                    .show()
                GlobalScope.launch {
                    myDatabase.dbDao().update(
                        NoteEntity(
                            position + 1,
                            binding.createTitle.text.toString(),
                            binding.createPriority.text.toString()
                        )
                    )
                }

                myIntent()
            }
        }
    }

    fun myIntent() {
        val intent = Intent(this@UpdateCardActivity, MainActivity::class.java)
        startActivity(intent)
    }
}