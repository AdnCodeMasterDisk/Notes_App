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

    fun getAllNotes(): LiveData<List<NotesEntity>> {
        return repository.getAllNotes()
    }

    fun getHigh(): LiveData<List<NotesEntity>> {
        return repository.getHigh()
    }

    fun getMed(): LiveData<List<NotesEntity>> {
        return repository.getMed()
    }

    fun getLow(): LiveData<List<NotesEntity>> {
        return repository.getLow()
    }

    fun search(searchQuery: String): LiveData<List<NotesEntity>> {
        return repository.searchDatabase(searchQuery)
    }

}