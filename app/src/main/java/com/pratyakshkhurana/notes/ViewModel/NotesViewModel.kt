package com.pratyakshkhurana.notes.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.pratyakshkhurana.notes.Data_or_Model.NotesDatabase
import com.pratyakshkhurana.notes.Data_or_Model.NotesEntity
import com.pratyakshkhurana.notes.Data_or_Model.Repository

class NotesViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: Repository
    private val allNotes: LiveData<List<NotesEntity>>

    init {
        val dao = NotesDatabase.getDatabase(application).notesDAO
        repository = Repository(dao)
        allNotes = repository.getAllNotes()
    }

    fun insert(note: NotesEntity) {
        repository.insert(note)
    }

    fun delete(note: NotesEntity) {
        repository.delete(note)
    }

    fun getNoteById(id: Int): NotesEntity? {
        return repository.getNoteById(id)
    }

}