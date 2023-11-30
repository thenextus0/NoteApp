package com.thenextus.noteapp.Database

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class NoteViewModelFactory(private val noteRepository: NoteRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NoteViewModel::class.java)) {
            return NoteViewModel(noteRepository) as T
        }
        throw IllegalArgumentException("Bilinmeyen model.")
    }

}