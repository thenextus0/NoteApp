package com.thenextus.noteapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.thenextus.noteapp.Classes.Database.NotesDAO
import com.thenextus.noteapp.Classes.KeyValues
import com.thenextus.noteapp.Classes.Note
import com.thenextus.noteapp.Classes.NoteViewModel
import com.thenextus.noteapp.Fragments.MainMenuFragment
import com.thenextus.noteapp.databinding.ActivityFragmentBinding
import java.security.Key
import java.util.UUID

class FragmentActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFragmentBinding

    private lateinit var noteViewModel: NoteViewModel

    private lateinit var notesDAO: NotesDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFragmentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val notesDAO = NotesDAO(this)

        noteViewModel = NoteViewModel()
        noteViewModel.setNotes(notesDAO.getAllNotes())

        /* database testi için Log testi.
        val allNotes = notesDAO.getAllNotes()
        for (note in allNotes) { Log.d("DBTEST", "UUID: ${note.noteID.toString()} Title: ${note.title}") }
        */

        //noteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java) //dataBase öncesi

        if (savedInstanceState != null) { noteViewModel.setNotes(savedInstanceState.getSerializable(KeyValues.InstanceStateKey.key) as ArrayList<Note>) }

        /* NavGraph ile Activity yüklendiğinde başlangıç Fragment'ı otomatik olarak gelecek.
        val maninMenuFragment = MainMenuFragment()
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(binding.frameLayout.id, maninMenuFragment).commit()
        */
    }

    fun getNoteViewModel(): NoteViewModel {
        return noteViewModel
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putSerializable(KeyValues.InstanceStateKey.key, noteViewModel.getNotes())
        super.onSaveInstanceState(outState)
    }




}
