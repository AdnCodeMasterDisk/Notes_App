package com.company_name.notesfinal.Model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NotesDao {
    //for accessing the table and database

    @Query("SELECT * FROM notesTable ORDER BY lastUpdatedTime")
    fun getAllNotes(): LiveData<List<NotesEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNote(note: NotesEntity)

    @Delete
    fun deleteNote(note: NotesEntity)

    @Query("SELECT * FROM notesTable WHERE priority='1'")
    fun getHighPriorityNotes(): LiveData<List<NotesEntity>>

    @Query("SELECT * FROM notesTable WHERE priority='2'")
    fun getMediumPriorityNotes(): LiveData<List<NotesEntity>>

    @Query("SELECT * FROM notesTable WHERE priority='3'")
    fun getLowPriorityNotes(): LiveData<List<NotesEntity>>

    // and then search query will be passed through
    // the parameter of this function
    // and then function will return the flow of list of person
    @Query("SELECT * FROM notesTable WHERE title LIKE :searchQuery")
    fun searchNote(searchQuery: String): LiveData<List<NotesEntity>>
    //LiveData<List<NotesEntity>> (lifecycle aware)
    //any change in table , activity should know , so LiveData is created, we
    //can observe LiveData from anywhere
    //and  make activity our observer

    @Update
    fun updateNote(note: NotesEntity)
}