package com.thenextus.noteapp.Classes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NoteViewModel: ViewModel() {
    private var noteList = ArrayList<Note>()

    fun getNotes(): ArrayList<Note> {
        return noteList
    }

    fun setNotes(savedInstanceNotes: ArrayList<Note>) {
        noteList = savedInstanceNotes
    }

    fun addNote(newNote: Note) {
        noteList.add(newNote)
    }

    fun removeNote(position: Int) {
        noteList.removeAt(position)
    }

    fun changeNote(newNote: Note, position: Int) {
        noteList[position] = newNote
    }

    fun getSpecificNote(position: Int): Note {
        return noteList[position]
    }

}