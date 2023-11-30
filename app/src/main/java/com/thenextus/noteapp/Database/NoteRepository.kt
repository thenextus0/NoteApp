package com.thenextus.noteapp.Database

import androidx.lifecycle.LiveData

class NoteRepository(private  val noteDao: NoteDao) {

    fun getAllNotes(): LiveData<List<Note>>? {
        return noteDao.getAllNotes()
    }

    fun getSpesificNote(noteID: String): LiveData<Note> {
        return noteDao.getSpesificNote(noteID)
    }

    suspend fun insertNote(note: Note) {
        noteDao.insertNote(note.noteID, note.title, note.content)
    }

    suspend fun updateNote(note: Note) {
        noteDao.updateNote(note.noteID, note.title, note.content)
    }

    suspend fun deleteNote(noteID: String) {
        noteDao.deleteNote(noteID)
    }
}