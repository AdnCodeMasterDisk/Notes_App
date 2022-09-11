package com.company_name.notesfinal.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.company_name.notesfinal.Model.NotesDatabase
import com.company_name.notesfinal.Model.NotesEntity
import com.company_name.notesfinal.Model.Repository


///it is lifecycle aware, knows when to supply data to activity
class NotesViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: Repository
    private val allNotes: LiveData<List<NotesEntity>>

    init {
        val dao = NotesDatabase.getDatabase(application).notesDAO
        repository = Repository(dao)
        allNotes = repository.getAllNotes()
    }

    fun insertNote(note: NotesEntity) {
        repository.insertNote(note)
    }

    fun deleteNote(note: NotesEntity) {
        repository.deleteNote(note)
    }

    fun getAllNotes(): LiveData<List<NotesEntity>> {
        return repository.getAllNotes()
    }

    fun getHighPriorityNotes(): LiveData<List<NotesEntity>> {
        return repository.getHighPriorityNotes()
    }

    fun getMediumPriorityNotes(): LiveData<List<NotesEntity>> {
        return repository.getMediumPriorityNotes()
    }

    fun getLowPriorityNotes(): LiveData<List<NotesEntity>> {
        return repository.getLowPriorityNotes()
    }

    fun searchNote(searchQuery: String): LiveData<List<NotesEntity>> {
        return repository.searchNote(searchQuery)
    }

    fun updateNote(note: NotesEntity) {
        repository.update(note)
    }
}