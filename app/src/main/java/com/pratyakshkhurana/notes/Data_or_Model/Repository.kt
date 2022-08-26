package com.pratyakshkhurana.notes.Data_or_Model

import androidx.lifecycle.LiveData

//constructor injection
class Repository(private val dao: NotesDAO) {

    fun getAllNotes(): LiveData<List<NotesEntity>> {
        return dao.getAllNotes()
    }

    fun insert(note: NotesEntity) {
        dao.insert(note)
    }

    fun delete(note: NotesEntity) {
        dao.delete(note)
    }

    fun getNoteById(id: Int): NotesEntity? {
        return dao.getNoteById(id)
    }
}