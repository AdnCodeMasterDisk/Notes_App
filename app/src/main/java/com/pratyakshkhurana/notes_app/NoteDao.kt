package com.pratyakshkhurana.notes_app

import androidx.room.*

@Dao
interface NoteDao {

    //@Insert - inserts a note in table
    //annotate them it will perform as written
    //add a conflict to allow notes with same name or not ,ignored as of now
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(note: Note)

    @Delete
    fun delete(note: Note)

    @Query("SELECT * FROM notes_table ORDER BY id ASC")
    fun getAllNotes(): List<Note>
}