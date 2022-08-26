package com.pratyakshkhurana.notes_application.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.pratyakshkhurana.notes_application.Database.NotesDatabase
import com.pratyakshkhurana.notes_application.Model.Notes
import com.pratyakshkhurana.notes_application.Repository.NotesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotesViewModel(application: Application) : AndroidViewModel(application) {

    //to access data from repository , instance is created of NotesRepository
    private val repository: NotesRepository

    init {
        val dao = NotesDatabase.getDatabase(application).getNotesDao()
        repository = NotesRepository(dao)
    }

    fun addNotes(notes: Notes) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertNotes(notes)
    }

    fun getNotes(): LiveData<List<Notes>> = repository.getAllNotes()

    fun deleteNote(id: Int) = viewModelScope.launch(Dispatchers.IO){
        repository.deleteNote(id)
    }


    fun getRedNotes(): LiveData<List<Notes>> {
        return repository.getRNotes()
    }

    fun getYellowNotes(): LiveData<List<Notes>> {
        return repository.getYNotes()
    }
    fun getGreenNotes(): LiveData<List<Notes>> {
        return repository.getGNotes()
    }



    fun updateNote(note: Notes) {
        repository.updateNotes(note)
    }

}