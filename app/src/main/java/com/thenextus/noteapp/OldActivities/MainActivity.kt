package com.thenextus.noteapp.OldActivities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.thenextus.noteapp.FragmentActivity
import com.thenextus.noteapp.Classes.KeyValues
import com.thenextus.noteapp.Classes.Note
import com.thenextus.noteapp.Classes.NoteSharedPreferences
import com.thenextus.noteapp.OldActivities.OldClasses.Observer
import com.thenextus.noteapp.OldActivities.OldClasses.RecyclerViewAdapter
import com.thenextus.noteapp.OldActivities.OldClasses.Subject
import com.thenextus.noteapp.databinding.ActivityMainBinding
import java.util.UUID

object DataSubject: Subject()

class MainActivity : AppCompatActivity(), Observer {

    private lateinit var binding: ActivityMainBinding
    private lateinit var recyclerViewAdapter: RecyclerViewAdapter
    private lateinit var noteList: ArrayList<Note>
    private var isObserverAdded: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //binding.button2.setOnClickListener {startActivity(Intent(this, FragmentActivity::class.java)); }


        val noteSharedPreferences = NoteSharedPreferences(this@MainActivity)
        noteList =  noteSharedPreferences.getArrayList(KeyValues.SharedPreferencesNotes.key)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerViewAdapter = RecyclerViewAdapter(noteList)
        binding.recyclerView.adapter = recyclerViewAdapter

        binding.button2.setOnClickListener {startActivity(Intent(this, NewNoteActivity::class.java)); }

        //binding.recyclerView.adapter



        if (savedInstanceState != null) {
            savedInstanceState.getSerializable(KeyValues.InstanceStateKey.key)?.let {

                val savedNoteList = savedInstanceState.getSerializable( KeyValues.InstanceStateKey.key) as ArrayList<Note>
                Log.d(KeyValues.LogTag.key, savedNoteList[0].title)

                noteList = savedNoteList
                recyclerViewAdapter = RecyclerViewAdapter(noteList)
                binding.recyclerView.adapter = recyclerViewAdapter
                isObserverAdded = true
            }
        }

        if (!isObserverAdded) { DataSubject.addObserver(this) }



    }

    override fun update(data: String, position: Int, titleData: String, forDelete: Boolean) {

        if (forDelete) {
            //noteList.removeAt(position)
            for (i in 0 until noteList.size) {
                Log.d("FTest", "İndex: $i | Title: ${noteList[i].title}")
            }

        } else {
            if (position == -1) { noteList.add(Note(UUID.randomUUID().toString(), titleData, data)) }
            else {
                noteList[position].title = titleData
                noteList[position].content = data;
                //recyclerViewAdapter.noteList = noteList
                Log.d("NewData", data)



            }
        }

        val noteSharedPreferences = NoteSharedPreferences(this@MainActivity)
        noteSharedPreferences.saveArrayList(KeyValues.SharedPreferencesNotes.key, noteList)

        if (!forDelete && position == -1) {
            recyclerViewAdapter = RecyclerViewAdapter(noteList)
            binding.recyclerView.adapter = recyclerViewAdapter

        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(KeyValues.InstanceStateKey.key, noteList)
        recyclerViewAdapter.NotifyChange(noteList)
    }



}