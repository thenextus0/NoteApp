package com.thenextus.noteapp.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.thenextus.noteapp.Classes.ServiceLocator
import com.thenextus.noteapp.Database.Note
import com.thenextus.noteapp.Database.NoteViewModel
import com.thenextus.noteapp.Database.NoteViewModelFactory
import com.thenextus.noteapp.databinding.FragmentShowNoteBinding

class ShowNoteFragment : Fragment() {

    private var _binding: FragmentShowNoteBinding? = null
    private val binding get() = _binding!!
    private lateinit var noteViewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentShowNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun returnToMainMenu(view: View) {
        val action = ShowNoteFragmentDirections.actionShowNoteFragmentToMainMenuFragment()
        Navigation.findNavController(view).navigate(action)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        noteViewModel = ViewModelProvider(requireActivity(), NoteViewModelFactory(ServiceLocator.provideNoteRepository())).get(NoteViewModel::class.java)

        var noteID: String = ""
        arguments?.let { noteID = ShowNoteFragmentArgs.fromBundle(it).noteID as String }

        var note: LiveData<Note>
        noteViewModel.getSpesificNote(noteID).observe(viewLifecycleOwner, Observer{
            note = noteViewModel.getSpesificNote(noteID)

            binding.textView.setText(it.title)
            binding.editTextText.setText(it.content)
        })

        binding.buttonCancel.setOnClickListener { returnToMainMenu(it) }
        binding.buttonEdit.setOnClickListener {
            val action = ShowNoteFragmentDirections.actionShowNoteFragmentToEditNoteFragment(noteID)
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}