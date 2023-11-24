package com.thenextus.noteapp.Classes.Database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.util.Log
import com.thenextus.noteapp.Classes.Note
import org.w3c.dom.Text
import java.util.UUID

class NotesDAO(context: Context) {

    private val dbHelper = DatabaseHelper(context)

    fun insertNote(note: Note): Long {
        val db = dbHelper.writableDatabase
        Log.d("DBTEST", "inser başı")
        val values = ContentValues().apply {
            put("noteID", note.noteID.toString())
            put("title", note.title)
            put("content", note.content)
        }
        Log.d("DBTEST", "inser sonu")
        return db.insert("Notes", null, values)
    }


    fun deleteNoteByID(noteID: String): Int {
        val db = dbHelper.writableDatabase
        val whereClause = "noteID = ?"
        val whereArgs = arrayOf(noteID)
        return db.delete("Notes", whereClause, whereArgs)
    }

    fun updateNoteTitleByID(noteID: String, title: String): Int {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("title", title)
        }

        val whereClause = "noteID = ?"
        val whereArgs = arrayOf(noteID)

        return  db.update("Notes", values, whereClause, whereArgs)
    }

    fun updateNoteContentByID(noteID: String, content: String): Int {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("content", content)
        }

        val whereClause = "noteID = ?"
        val whereArgs = arrayOf(noteID)

        return db.update("Notes", values, whereClause, whereArgs)
    }

    fun updateNoteTitleAndContentByID(note: Note): Int {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("title", note.title)
            put("content", note.content)
        }

        val whereClause = "noteID = ?"
        val whereArgs = arrayOf(note.noteID)

        return db.update("Notes", values, whereClause, whereArgs)
    }

    fun getAllNotes(): ArrayList<Note> {
        val notesFromDB = ArrayList<Note>()
        val db = dbHelper.readableDatabase
        val cursor: Cursor = db.rawQuery("SELECT * FROM Notes", null)

        while (cursor.moveToNext()) {
            val note: Note = Note(cursor.getString(cursor.getColumnIndex("noteID").toInt()), cursor.getString(cursor.getColumnIndex("title").toInt()), cursor.getString(cursor.getColumnIndex("content").toInt()))
            notesFromDB.add(note)
        }
        cursor.close()
        return notesFromDB
    }
}