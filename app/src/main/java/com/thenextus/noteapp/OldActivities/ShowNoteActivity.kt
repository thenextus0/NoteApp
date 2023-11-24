package com.thenextus.noteapp.OldActivities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.thenextus.noteapp.Classes.KeyValues
import com.thenextus.noteapp.databinding.ActivityShowNoteBinding

class ShowNoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityShowNoteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShowNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = intent
        val note = intent.getStringExtra(KeyValues.NoteContent.key)
        val title = intent.getStringExtra(KeyValues.NoteTitle.key)
        val position = intent.getStringExtra(KeyValues.NotePosition.key)


        if (note != null && position != null && title != null) {
            binding.editTextText.setText(note.toString())
            binding.textView.setText(title.toString())
        }
        
        binding.buttonCancel.setOnClickListener { finish() }

        binding.buttonEdit.setOnClickListener {
            val intent = Intent(this@ShowNoteActivity, EditNoteActivity::class.java).apply {
                putExtra(KeyValues.NoteContent.key, note.toString())
                putExtra(KeyValues.NoteTitle.key, title.toString())
                putExtra(KeyValues.NotePosition.key, position.toString())
            }
            startActivity(intent)
            finish()
        }

    }
}