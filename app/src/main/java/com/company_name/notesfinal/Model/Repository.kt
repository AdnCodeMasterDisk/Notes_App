package com.company_name.notesfinal.Model

import androidx.lifecycle.LiveData


//constructor injection
//single source of truth for all types of data
class Repository(private val dao: NotesDao) {

    fun getAllNotes(): LiveData<List<NotesEntity>> {
        return dao.getAllNotes()
    }

    fun insertNote(note: NotesEntity) {
        dao.insertNote(note)
    }

    fun deleteNote(note: NotesEntity) {
        dao.deleteNote(note)
    }

    fun getHighPriorityNotes(): LiveData<List<NotesEntity>> {
        return dao.getHighPriorityNotes()
    }

    fun getMediumPriorityNotes(): LiveData<List<NotesEntity>> {
        return dao.getMediumPriorityNotes()
    }

    fun getLowPriorityNotes(): LiveData<List<NotesEntity>> {
        return dao.getLowPriorityNotes()
    }

    fun searchNote(searchQuery: String): LiveData<List<NotesEntity>> {
        return dao.searchNote(searchQuery)
    }

    fun update(note: NotesEntity) {
        dao.updateNote(note)
    }
}