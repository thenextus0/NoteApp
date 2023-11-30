package com.thenextus.noteapp.Database

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class NoteViewModel(private val repository: NoteRepository): ViewModel() {

    var allNotes: LiveData<List<Note>>? = repository.getAllNotes()

    fun getSpesificNote(noteID: String) = repository.getSpesificNote(noteID)


    fun insertNote(note: Note) = viewModelScope.launch {
        repository.insertNote(note)
    }

    fun updateNote(note: Note) = viewModelScope.launch {
        repository.updateNote(note)
    }

    fun deleteNote(noteID: String) = viewModelScope.launch {
        repository.deleteNote(noteID)
    }
}