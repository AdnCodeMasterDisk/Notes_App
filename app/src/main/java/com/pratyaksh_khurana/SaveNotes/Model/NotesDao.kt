package com.pratyaksh_khurana.SaveNotes.Model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NotesDao {
    //for accessing the table and database

    @Query("SELECT * FROM notesTable ORDER BY id desc")
    fun getAllNotes(): LiveData<List<NotesEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNote(note: NotesEntity)

    @Delete
    fun deleteNote(note: NotesEntity)

    @Query("SELECT * FROM notesTable WHERE priority='1' ORDER BY id desc")
    fun getHighPriorityNotes(): LiveData<List<NotesEntity>>

    @Query("SELECT * FROM notesTable WHERE priority='2' ORDER BY id desc")
    fun getMediumPriorityNotes(): LiveData<List<NotesEntity>>

    @Query("SELECT * FROM notesTable WHERE priority='3' ORDER BY id desc")
    fun getLowPriorityNotes(): LiveData<List<NotesEntity>>

    // and then search query will be passed through
    // the parameter of this function
    // and then function will return the flow of list of person
    @Query("SELECT * FROM notesTable WHERE title LIKE :searchQuery || '%' ORDER BY id desc")
    fun searchNote(searchQuery: String): LiveData<List<NotesEntity>>
    //LiveData<List<NotesEntity>> (lifecycle aware)
    //any change in table , activity should know , so LiveData is created, we
    //can observe LiveData from anywhere
    //and  make activity our observer

    @Update
    fun updateNote(note: NotesEntity)
}