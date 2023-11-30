package com.thenextus.noteapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.thenextus.noteapp.Database.NoteViewModel
import com.thenextus.noteapp.databinding.ActivityFragmentBinding

class FragmentActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFragmentBinding

    //private lateinit var noteViewModel: NoteViewModel

    //private lateinit var notesDAO: NotesDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFragmentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //noteViewModel = ViewModelProvider(this, NoteViewModelFactory(ServiceLocator.provideNoteRepository())).get(NoteViewModel::class.java)

        //val notesDAO = NotesDAO(this)

        //noteViewModel = NoteViewModel()
        //noteViewModel.setNotes(notesDAO.getAllNotes())

        /* database testi için Log testi.
        val allNotes = notesDAO.getAllNotes()
        for (note in allNotes) { Log.d("DBTEST", "UUID: ${note.noteID.toString()} Title: ${note.title}") }
        */

        //noteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java) //dataBase öncesi

        //if (savedInstanceState != null) { noteViewModel.setNotes(savedInstanceState.getSerializable(KeyValues.InstanceStateKey.key) as ArrayList<Note>) }

    }

    /*fun getNoteViewModel(): NoteViewModel {
        return noteViewModel
    }

     */

    /*override fun onSaveInstanceState(outState: Bundle) {
        outState.putSerializable(KeyValues.InstanceStateKey.key, noteViewModel.getNotes())
        super.onSaveInstanceState(outState)
    }

     */




}
