package com.pratyakshkhurana.notes_application.Dao

import android.provider.ContactsContract
import androidx.lifecycle.LiveData
import androidx.room.*
import com.pratyakshkhurana.notes_application.Model.Notes


//@Insert - inserts a note in table (a column)
//annotate them it will perform as written
//add a conflict to allow notes with same name to insert in database or not ,ignored as of now

//suspend keyword added so that these functions perform in background thread
//as they are performing I/O operations which are heavy
//suspend is a coroutine (background process made easy)

//we need queries to fetch data from database
@Dao
interface NotesDao {
    //activity should be notified when data is modified in table so make it LiveData
    //activity observes LiveData

    //get all notes from table (entity)
    @Query("SELECT * FROM notes_table")
    fun getNotes(): LiveData<List<Notes>>

    //if same query comes, it will replace
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(note: Notes)

    @Query("DELETE FROM notes_table WHERE id=:id")
    fun delete(id: Int)

    @Update
    fun updateNotes(note: Notes)


    @Query("SELECT * FROM notes_table WHERE priority = 1")
    fun getYellowNotes(): LiveData<List<Notes>>


    @Query("SELECT * FROM notes_table WHERE priority = 2")
    fun getGreenNotes(): LiveData<List<Notes>>


    @Query("SELECT * FROM notes_table WHERE priority = 3")
    fun getRedNotes(): LiveData<List<Notes>>


}