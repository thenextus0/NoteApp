package com.thenextus.noteapp.Classes

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class NoteSharedPreferences(context: Context) {

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(KeyValues.SharedPreferencesNotes.key, Context.MODE_PRIVATE)
    private val gson = Gson()

    fun saveArrayList(key: String, notes: ArrayList<Note>) {
        val editor = sharedPreferences.edit()
        val json = gson.toJson(notes)
        editor.putString(key, json)
        editor.apply()
    }

    fun getArrayList(key: String): ArrayList<Note> {
        val json = sharedPreferences.getString(key, "")
        val type = object : TypeToken<ArrayList<Note>>() {}.type
        return gson.fromJson(json, type) ?: ArrayList<Note>()
    }
}