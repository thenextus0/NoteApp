package com.thenextus.noteapp.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.thenextus.noteapp.Classes.ServiceLocator
import com.thenextus.noteapp.Database.Note
import com.thenextus.noteapp.Database.NoteViewModel
import com.thenextus.noteapp.Database.NoteViewModelFactory
import com.thenextus.noteapp.FragmentActivity
import com.thenextus.noteapp.databinding.FragmentEditNoteBinding

class EditNoteFragment : Fragment() {

    private var _binding: FragmentEditNoteBinding? = null
    private val binding get() = _binding!!
    private lateinit var noteViewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentEditNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        noteViewModel = ViewModelProvider(requireActivity(), NoteViewModelFactory(ServiceLocator.provideNoteRepository())).get(NoteViewModel::class.java)
        var noteID: String = ""
        arguments?.let { noteID = EditNoteFragmentArgs.fromBundle(it).noteID as String }

        noteViewModel.getSpesificNote(noteID).observe(viewLifecycleOwner, Observer {
            //var note = noteViewModel.getSpesificNote(noteID)
            binding.textView.setText(it.title)
            binding.editTextText.setText(it.content)

            binding.buttonSaveO.setOnClickListener {view ->

                if ((it.content != binding.editTextText.text.toString() || it.title != binding.textView.text.toString()) &&
                    binding.editTextText.text.toString().replace("\\s".toRegex(), "") != "" &&
                    binding.textView.text.toString().replace("\\s".toRegex(), "") != ""
                ) {
                    noteViewModel.updateNote(Note(it.noteID, binding.textView.text.toString(), binding.editTextText.text.toString()))
                    returnToMainMenu(view)
                } else if (it.content == binding.editTextText.text.toString() && it.title == binding.textView.text.toString()) {
                    Toast.makeText(activity, "Hiçbir alanda değişiklik yapmadınız.", Toast.LENGTH_SHORT).show()
                } else if (binding.editTextText.text.toString().replace("\\s".toRegex(), "") == "" || binding.textView.text.toString().replace("\\s".toRegex(), "") == "") {
                    Toast.makeText(activity, "Lütfen tüm alanları doldurunuz!", Toast.LENGTH_SHORT).show()
                }
            }
        })
        binding.buttonCancel.setOnClickListener { returnToMainMenu(it) }
    }

    private fun returnToMainMenu(view: View) {
        val action = EditNoteFragmentDirections.actionEditNoteFragmentToMainMenuFragment()
        Navigation.findNavController(view).navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}