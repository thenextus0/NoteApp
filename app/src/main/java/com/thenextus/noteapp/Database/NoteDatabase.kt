package com.thenextus.noteapp.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase: RoomDatabase() {
    abstract fun noteDao(): NoteDao

    companion object {
        @Volatile
        private var instanceF: NoteDatabase? = null

        fun getInstance(context: Context): NoteDatabase {
            return instanceF ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NoteDatabase::class.java,
                    "NoteDatabase"
                ).build()
                instanceF = instance
                instance
            }
        }
    }
}