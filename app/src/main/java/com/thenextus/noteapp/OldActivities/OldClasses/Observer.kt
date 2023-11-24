package com.thenextus.noteapp.OldActivities.OldClasses

interface Observer {
    fun update(data: String, position: Int, titleData: String, forDelete: Boolean)
}