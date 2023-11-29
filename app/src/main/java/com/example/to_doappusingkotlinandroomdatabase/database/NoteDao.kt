package com.example.to_doappusingkotlinandroomdatabase.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.to_doappusingkotlinandroomdatabase.model.CardInfo

@Dao
interface NoteDao {

    @Insert
   suspend fun insert(noteEntity: NoteEntity)

    @Update
   suspend fun update(entity: NoteEntity)

    @Delete
  suspend  fun delete(entity: NoteEntity)

    @Query("DELETE  FROM  TO_DO_NOTE")
  suspend  fun deleteAll()

    @Query("Select * from TO_DO_NOTE")
   fun getAllTask(): LiveData<List<CardInfo>>
}