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

    @Query("SELECT * FROM notesTable WHERE priority='1'")
    fun getHigh(): LiveData<List<NotesEntity>>

    @Query("SELECT * FROM notesTable WHERE priority='2'")
    fun getMed(): LiveData<List<NotesEntity>>

    @Query("SELECT * FROM notesTable WHERE priority='3'")
    fun getLow(): LiveData<List<NotesEntity>>

    @Query("SELECT * FROM notesTable WHERE title LIKE :searchQuery")
    // and then search query will be passed through
    // the perimeter of this function
    // and then function will return the flow of list of person
    fun searchDatabase(searchQuery: String): LiveData<List<NotesEntity>>

    @Update
    fun update(note: NotesEntity)

}


//@Insert - inserts a note in table (a column)
//annotate them it will perform as written
//add a conflict to allow notes with same name to insert in database or not ,ignored as of now

//suspend keyword added so that these functions perform in background thread
//as they are performing I/O operations which are heavy
//suspend is a coroutine (background process made easy)

//we need queries to fetch data from database
