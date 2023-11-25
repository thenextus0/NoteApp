package com.thenextus.noteapp.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.Navigation
import com.thenextus.noteapp.Classes.Database.NotesDAO
import com.thenextus.noteapp.Classes.Note
import com.thenextus.noteapp.Classes.NoteViewModel
import com.thenextus.noteapp.FragmentActivity
import com.thenextus.noteapp.R
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

        binding.buttonCancel.setOnClickListener {
            returnToMainMenu(it) }

        binding.buttonSaveNew.setOnClickListener {
            noteViewModel = (activity as FragmentActivity).getNoteViewModel()

            if (binding.editTextText.text.toString().replace("\\s".toRegex(), "") != "" &&
                binding.textView.text.toString().replace("\\s".toRegex(), "") != "") {
                noteViewModel.addNote(Note(UUID.randomUUID().toString(), binding.textView.text.toString(), binding.editTextText.text.toString()))

                val notesDAO = NotesDAO(requireActivity())
                notesDAO.insertNote(Note(UUID.randomUUID().toString(), binding.textView.text.toString(), binding.editTextText.text.toString()))

                returnToMainMenu(it)
            }
            else { Toast.makeText(activity, "Lütfen tüm alanları doldurunuz.", Toast.LENGTH_SHORT).show() }
        }
        return view
    }

    private fun returnToMainMenu(view: View) {
        val action = NewNoteFragmentDirections.actionNewNoteFragmentToMainMenuFragment()
        Navigation.findNavController(view).navigate(action)

        /* NavGraph öncesi.
        val mainMenuFragment = MainMenuFragment()
        val fragmentTransaction: FragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()

        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        fragmentTransaction.replace(R.id.frameLayout, mainMenuFragment)

        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
        */
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}