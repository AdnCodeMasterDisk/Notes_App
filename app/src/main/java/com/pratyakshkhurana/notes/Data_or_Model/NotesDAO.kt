package com.pratyakshkhurana.notes.Data_or_Model

import androidx.lifecycle.LiveData
import androidx.room.*

//for accessing database
@Dao
interface NotesDAO {

    @Query("SELECT * FROM notesTable")
    fun getAllNotes(): LiveData<List<NotesEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(note: NotesEntity)

    @Delete
    fun delete(note: NotesEntity)

    @Query("SELECT * FROM notesTable WHERE id=:id")
    fun getNoteById(id: Int): NotesEntity?

}


//@Insert - inserts a note in table (a column)
//annotate them it will perform as written
//add a conflict to allow notes with same name to insert in database or not ,ignored as of now

//suspend keyword added so that these functions perform in background thread
//as they are performing I/O operations which are heavy
//suspend is a coroutine (background process made easy)

//we need queries to fetch data from database
