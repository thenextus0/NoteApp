package com.thenextus.noteapp.Classes

import android.app.Application
import com.thenextus.noteapp.Database.NoteDatabase

class NoteApp: Application() {

    override fun onCreate() {
        super.onCreate()

        val noteDatabase = NoteDatabase.getInstance(this)
        ServiceLocator.initDatabase(noteDatabase)
    }
}   