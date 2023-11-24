package com.thenextus.noteapp.Classes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.thenextus.noteapp.databinding.RecyclerviewrowBinding

class NoteRecyclerViewAdapter(var noteList: ArrayList<Note>, private val itemClickListener: OnItemClickListener): RecyclerView.Adapter<NoteRecyclerViewAdapter.NoteRowHolder>() {

    private var onDeleteClickListener: OnDeleteClickListener? = null

    inner class NoteRowHolder(val binding: RecyclerviewrowBinding): RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                itemClickListener.onItemClick(adapterPosition)
            }

            binding.rowDeleteButton.setOnClickListener {
                onDeleteClickListener?.onDeleteClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteRowHolder {
        val binding = RecyclerviewrowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteRowHolder(binding)
    }

    override fun getItemCount(): Int { return noteList.size }

    override fun onBindViewHolder(holder: NoteRowHolder, position: Int) {

        holder.binding.rowDeleteButton.setOnClickListener {
            onDeleteClickListener?.onDeleteClick(position)
        }

        holder.binding.textViewRowHead.text = noteList[position].title
        holder.binding.textViewRowNote.text = noteList[position].content

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