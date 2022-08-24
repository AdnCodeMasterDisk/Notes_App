package com.pratyakshkhurana.notes_app

import androidx.lifecycle.LiveData
import androidx.room.*

//to access the SQLite table
@Dao
interface NoteDao {

    //@Insert - inserts a note in table (a column)
    //annotate them it will perform as written
    //add a conflict to allow notes with same name to insert in database or not ,ignored as of now

    //suspend keyword added so that these functions perform in background thread
    //as they are performing I/O operations which are heavy
    //suspend is a coroutine (background process made easy)
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(note: Note)

    @Delete
    suspend fun delete(note: Note)

    @Query("SELECT * FROM notes_table ORDER BY id ASC")
    fun getAllNotes(): LiveData<List<Note>>

//    activity should be notified when data is modified in table so make it LiveData
// activity observes LiveData
}