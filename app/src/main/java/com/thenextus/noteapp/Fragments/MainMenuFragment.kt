package com.thenextus.noteapp.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.thenextus.noteapp.Classes.NoteRecyclerViewAdapter
import com.thenextus.noteapp.Database.Note
import com.thenextus.noteapp.Database.NoteViewModel
import com.thenextus.noteapp.Database.NoteViewModelFactory
import com.thenextus.noteapp.databinding.FragmentMainMenuBinding
import com.thenextus.noteapp.Classes.ServiceLocator

class MainMenuFragment : Fragment(), NoteRecyclerViewAdapter.OnDeleteClickListener {

    private var _binding: FragmentMainMenuBinding? = null
    private val binding get() = _binding!!
    private lateinit var notes: LiveData<List<Note>>

    private lateinit var noteRecyclerViewAdapter: NoteRecyclerViewAdapter
    private lateinit var noteViewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentMainMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        noteViewModel = ViewModelProvider(requireActivity(), NoteViewModelFactory(ServiceLocator.provideNoteRepository())).get(NoteViewModel::class.java)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        noteViewModel.allNotes?.observe(viewLifecycleOwner, Observer {
            notes = noteViewModel.allNotes!!
            noteRecyclerViewAdapter = NoteRecyclerViewAdapter(notes, object : NoteRecyclerViewAdapter.OnItemClickListener {
                override fun onItemClick(position: Int) {
                    val action = MainMenuFragmentDirections.actionMainMenuFragmentToShowNoteFragment(it[position].noteID)
                    Navigation.findNavController(requireView()).navigate(action)
                }
            })

            noteRecyclerViewAdapter.setOnDeleteClickListener(this)
            binding.recyclerView.adapter = noteRecyclerViewAdapter
        })

        binding.button2.setOnClickListener {
            val action = MainMenuFragmentDirections.actionMainMenuFragmentToNewNoteFragment()
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDeleteClick(position: Int) {
        noteViewModel.deleteNote(notes.value!![position].noteID)
    }
}