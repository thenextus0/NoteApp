package com.thenextus.noteapp.Database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface NoteDao {

    @Query("SELECT * FROM Notes")
    fun getAllNotes(): LiveData<List<Note>>?

    @Query("SELECT * FROM Notes WHERE noteID=:noteID")
    fun getSpesificNote(noteID: String): LiveData<Note>

    //@Insert(onConflict = OnConflictStrategy.REPLACE)
    //suspend fun insert(note: Note)

    @Query("INSERT INTO Notes (noteID, title, content) VALUES (:id, :newTitle, :newContent)")
    suspend fun insertNote(id: String, newTitle: String, newContent: String)

    //@Update
    //suspend fun update(note: Note)

    @Query("UPDATE Notes SET title= :newTitle, content= :newContent WHERE noteID= :id")
    suspend fun updateNote(id: String, newTitle: String, newContent: String)

    //@Delete
    //suspend fun delete(note: Note)

    @Query("DELETE FROM Notes WHERE noteID= :id")
    suspend fun deleteNote(id: String)

}