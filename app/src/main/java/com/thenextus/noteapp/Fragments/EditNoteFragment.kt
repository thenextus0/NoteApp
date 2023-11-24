package com.thenextus.noteapp.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import com.thenextus.noteapp.Classes.Database.NotesDAO
import com.thenextus.noteapp.Classes.KeyValues
import com.thenextus.noteapp.Classes.Note
import com.thenextus.noteapp.FragmentActivity
import com.thenextus.noteapp.OldActivities.DataSubject
import com.thenextus.noteapp.R
import com.thenextus.noteapp.databinding.FragmentEditNoteBinding

class EditNoteFragment : Fragment() {

    private var _binding: FragmentEditNoteBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentEditNoteBinding.inflate(inflater, container, false)
        val view = binding.root

        val position = arguments?.getInt(KeyValues.NotePositionBundle.key) as Int
        val noteViewModel = (activity as FragmentActivity).getNoteViewModel()
        val note = noteViewModel.getSpecificNote(position)

        binding.textView.setText(note.title)
        binding.editTextText.setText(note.content)

        binding.buttonCancel.setOnClickListener { returnToMainMenu() }

        binding.buttonSaveO.setOnClickListener {

            if ((note.content != binding.editTextText.text.toString() || note.title != binding.textView.text.toString()) &&
                binding.editTextText.text.toString().replace("\\s".toRegex(), "") != "" &&
                binding.textView.text.toString().replace("\\s".toRegex(), "") != ""
            ) {
                noteViewModel.changeNote(Note(note.noteID, binding.textView.text.toString(), binding.editTextText.text.toString()), position)
                val notesDAO = NotesDAO(requireActivity())
                notesDAO.updateNoteTitleAndContentByID(Note(note.noteID, binding.textView.text.toString(), binding.editTextText.text.toString()))

                returnToMainMenu()
            } else if (note.content == binding.editTextText.text.toString() && note.title == binding.textView.text.toString()) {
                Toast.makeText(activity, "Hiçbir alanda değişiklik yapmadınız.", Toast.LENGTH_SHORT).show()
            } else if (binding.editTextText.text.toString().replace("\\s".toRegex(), "") == "" || binding.textView.text.toString().replace("\\s".toRegex(), "") == "") {
                Toast.makeText(activity, "Lütfen tüm alanları doldurunuz!", Toast.LENGTH_SHORT).show()
            }
        }
        return view
    }

    private fun returnToMainMenu() {
        val mainMenuFragment = MainMenuFragment()
        val fragmentTransaction: FragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()

        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        fragmentTransaction.replace(R.id.frameLayout, mainMenuFragment)

        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}