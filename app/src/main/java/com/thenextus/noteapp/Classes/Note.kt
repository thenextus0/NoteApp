package com.thenextus.noteapp.Classes

import java.io.Serializable

class Note(noteID: String, title: String, content: String): Serializable {
    var title: String
    var content: String
    val noteID: String

    init {
        this.title = title
        this.content = content
        this.noteID = noteID
    }
}