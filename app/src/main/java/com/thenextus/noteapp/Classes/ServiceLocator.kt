package com.thenextus.noteapp.Classes

import com.thenextus.noteapp.Database.NoteDatabase
import com.thenextus.noteapp.Database.NoteRepository

object ServiceLocator {
    private var noteDatabase: NoteDatabase? = null
    private var noteRepository: NoteRepository? = null

    fun initDatabase(noteDatabase: NoteDatabase) {
        this.noteDatabase = noteDatabase
        noteRepository = noteDatabase.noteDao().let { NoteRepository(it) }
    }

    fun provideNoteDatabase(): NoteDatabase {
        return noteDatabase ?: throw IllegalArgumentException("ServiceLocator not initialized")
    }

    fun provideNoteRepository(): NoteRepository {
        return noteRepository ?: throw IllegalArgumentException("ServiceLocator not initialized")
    }
}