package com.thenextus.noteapp.Database

import java.io.Serializable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Notes")
data class Note(
    @PrimaryKey @ColumnInfo(name = "noteID") var noteID: String,
    @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "content") var content: String
)