package com.thenextus.noteapp.Classes

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.thenextus.noteapp.Database.Note
import com.thenextus.noteapp.Database.NoteViewModel
import com.thenextus.noteapp.databinding.RecyclerviewrowBinding

class NoteRecyclerViewAdapter(var noteList: LiveData<List<Note>>, private val itemClickListener: OnItemClickListener): RecyclerView.Adapter<NoteRecyclerViewAdapter.NoteRowHolder>() {

    private var onDeleteClickListener: OnDeleteClickListener? = null

    inner class NoteRowHolder(val binding: RecyclerviewrowBinding): RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener { itemClickListener.onItemClick(adapterPosition) }
            binding.rowDeleteButton.setOnClickListener { onDeleteClickListener?.onDeleteClick(adapterPosition) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteRowHolder {
        val binding = RecyclerviewrowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteRowHolder(binding)
    }

    override fun getItemCount(): Int {
        if (noteList.isInitialized) { return noteList.value?.size ?: 0 }
        else { return 0 }
    }

    override fun onBindViewHolder(holder: NoteRowHolder, position: Int) {

        holder.binding.rowDeleteButton.setOnClickListener { onDeleteClickListener?.onDeleteClick(position) }
        if (noteList.isInitialized) {
            holder.binding.textViewRowHead.text = noteList.value!![position].title
            holder.binding.textViewRowNote.text = noteList.value!![position].content
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    interface OnDeleteClickListener {
        fun onDeleteClick(position: Int)
    }

    fun setOnDeleteClickListener(listener: OnDeleteClickListener) {
        this.onDeleteClickListener = listener
    }
}