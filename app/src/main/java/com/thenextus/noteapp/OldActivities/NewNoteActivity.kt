package com.thenextus.noteapp.OldActivities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.thenextus.noteapp.Classes.KeyValues
import com.thenextus.noteapp.OldActivities.OldClasses.Observer
import com.thenextus.noteapp.databinding.ActivityNewNoteBinding

class NewNoteActivity : AppCompatActivity(), Observer {

    private lateinit var binding: ActivityNewNoteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        DataSubject.addObserver(this)

        binding.buttonCancel.setOnClickListener { finish() }

        binding.buttonSaveNew.setOnClickListener {
            //yeni notu kaydet.

            if (binding.editTextText.text.toString().replace("\\s".toRegex(), "") != "" && binding.textView.text.toString().replace("\\s".toRegex(), "") != "") {
                DataSubject.notifyObservers("${binding.editTextText.text}", -1, "${binding.textView.text}", false) //eklenicek. titleData
                finish()
            }
            else { Toast.makeText(this@NewNoteActivity, "Lütfen tüm alanları doldurunuz.", Toast.LENGTH_SHORT).show() }
        }
    }

    override fun update(data: String, position: Int, titleData: String, forDelete: Boolean) {
        Log.d(KeyValues.LogTag.key, "Gelen Veri: $data")
    }
}