package com.thenextus.noteapp.OldActivities.OldClasses

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.thenextus.noteapp.Classes.KeyValues
import com.thenextus.noteapp.Classes.Note
import com.thenextus.noteapp.Classes.NoteSharedPreferences
import com.thenextus.noteapp.OldActivities.DataSubject
import com.thenextus.noteapp.OldActivities.ShowNoteActivity
import com.thenextus.noteapp.databinding.RecyclerviewrowBinding

class RecyclerViewAdapter(var noteList: ArrayList<Note>) : RecyclerView.Adapter<RecyclerViewAdapter.RowHolder>() {

    class RowHolder(val binding: RecyclerviewrowBinding): RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowHolder {
        val binding = RecyclerviewrowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RowHolder(binding)
    }

    override fun getItemCount(): Int {
        return noteList.size
    }

    override fun onBindViewHolder(holder: RowHolder, position: Int) {
        holder.binding.textViewRowNote.text = noteList[position].content
        holder.binding.textViewRowHead.text = noteList[position].title

        holder.binding.rowDeleteButton.setOnClickListener {

            DataSubject.notifyObservers(noteList[position].content, position, noteList[position].title, true)
            noteList.removeAt(position)
            notifyDataSetChanged()
            val noteSharedPreferences = NoteSharedPreferences(holder.itemView.context)
            noteSharedPreferences.saveArrayList(KeyValues.SharedPreferencesNotes.key, noteList)
        }

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, ShowNoteActivity::class.java)
            intent.putExtra(KeyValues.NoteContent.key, noteList[position].content)
            intent.putExtra(KeyValues.NotePosition.key, position.toString())
            intent.putExtra(KeyValues.NoteTitle.key, noteList[position].title)
            holder.itemView.context.startActivity(intent)
        }

    }

    public fun NotifyChange(newNoteList: ArrayList<Note>) {
        Log.d("RecycleTest", "not")
        noteList = newNoteList
        notifyDataSetChanged()
    }
}