package com.thenextus.noteapp.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.thenextus.noteapp.Classes.ServiceLocator
import com.thenextus.noteapp.Database.Note
import com.thenextus.noteapp.Database.NoteViewModel
import com.thenextus.noteapp.Database.NoteViewModelFactory
import com.thenextus.noteapp.databinding.FragmentNewNoteBinding
import java.util.UUID

class NewNoteFragment : Fragment() {

    private var _binding: FragmentNewNoteBinding? = null
    private val binding get() = _binding!!

    lateinit var noteViewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentNewNoteBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    private fun returnToMainMenu(view: View) {
        val action = NewNoteFragmentDirections.actionNewNoteFragmentToMainMenuFragment()
        Navigation.findNavController(view).navigate(action)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonCancel.setOnClickListener { returnToMainMenu(it) }

        binding.buttonSaveNew.setOnClickListener {

            noteViewModel = ViewModelProvider(requireActivity(), NoteViewModelFactory(ServiceLocator.provideNoteRepository())).get(NoteViewModel::class.java)

            if (binding.editTextText.text.toString().replace("\\s".toRegex(), "") != "" &&
                binding.textView.text.toString().replace("\\s".toRegex(), "") != "") {
                noteViewModel.insertNote(Note(UUID.randomUUID().toString(), binding.textView.text.toString(), binding.editTextText.text.toString()))
                returnToMainMenu(it)
            }
            else { Toast.makeText(activity, "Lütfen tüm alanları doldurunuz.", Toast.LENGTH_SHORT).show() }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}