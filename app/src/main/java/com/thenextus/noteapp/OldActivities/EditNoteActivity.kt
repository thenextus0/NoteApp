package com.thenextus.noteapp.OldActivities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.thenextus.noteapp.Classes.KeyValues
import com.thenextus.noteapp.OldActivities.OldClasses.Observer
import com.thenextus.noteapp.databinding.ActivityEditNoteBinding

class EditNoteActivity : AppCompatActivity(), Observer {

    private lateinit var binding: ActivityEditNoteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        DataSubject.addObserver(this)

        val note = intent.getStringExtra(KeyValues.NoteContent.key)
        val title = intent.getStringExtra(KeyValues.NoteTitle.key)
        val position = intent.getStringExtra(KeyValues.NotePosition.key)

        if (note != null && position != null && title != null) {
            binding.textView.setText(title)
            binding.editTextText.setText(note);
        }

        binding.buttonCancel.setOnClickListener { finish() }

        binding.buttonSaveO.setOnClickListener {
            if ((note != binding.editTextText.text.toString() || title != binding.textView.text.toString()) &&
                binding.editTextText.text.toString().replace("\\s".toRegex(), "") != "" &&
                position != null &&
                binding.textView.text.toString().replace("\\s".toRegex(), "") != ""
                ) {
                Log.d("EditTest", "notify")
                DataSubject.notifyObservers("${binding.editTextText.text}", position.toInt(), "${binding.textView.text}", false) //güncellenecek
                finish()
            }
            else if (note == binding.editTextText.text.toString() && title == binding.textView.text.toString()) {
                Toast.makeText(this@EditNoteActivity, "Hiçbir alanda değişiklik yapmadınız.", Toast.LENGTH_SHORT).show()
            }
            else if (binding.editTextText.text.toString().replace("\\s".toRegex(), "") == "" || binding.textView.text.toString().replace("\\s".toRegex(), "") == "") {
                Toast.makeText(this@EditNoteActivity, "Lütfen tüm alanları doldurunuz!", Toast.LENGTH_SHORT).show()
            }
            else {
                Log.d("IfTest", "EditError")
            }
        }
    }

    override fun update(data: String, position: Int, titleData: String, forDelete: Boolean) {
        Log.d(KeyValues.LogTag.key, "Gelen Veri: $data")
    }
}