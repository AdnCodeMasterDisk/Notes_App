package com.pratyakshkhurana.notes_application.Repository

import androidx.lifecycle.LiveData
import com.pratyakshkhurana.notes_application.Dao.NotesDao
import com.pratyakshkhurana.notes_application.Model.Notes

//repository
//layer which provides a cleaner API
//all functions will be made using DAO , so DAO passed in constructor
class NotesRepository(private val dao: NotesDao) {

    fun getAllNotes(): LiveData<List<Notes>> {
        return dao.getNotes()
    }


    fun getRNotes(): LiveData<List<Notes>> {
        return dao.getRedNotes()
    }


    fun getYNotes(): LiveData<List<Notes>> {
        return dao.getYellowNotes()
    }


    fun getGNotes(): LiveData<List<Notes>> {
        return dao.getGreenNotes()
    }


    fun insertNotes(note: Notes) {
        //this function is called in DAO and query will be performed in database
        dao.insert(note)
    }

    fun deleteNote(id: Int) {
        dao.delete(id)
    }

    fun updateNotes(notes: Notes) {
        dao.updateNotes(notes)
    }


}