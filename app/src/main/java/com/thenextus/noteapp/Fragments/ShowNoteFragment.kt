package com.thenextus.noteapp.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.Navigation
import com.thenextus.noteapp.Classes.KeyValues
import com.thenextus.noteapp.FragmentActivity
import com.thenextus.noteapp.R
import com.thenextus.noteapp.databinding.FragmentShowNoteBinding

class ShowNoteFragment : Fragment() {

    private var _binding: FragmentShowNoteBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentShowNoteBinding.inflate(inflater, container, false)
        val view = binding.root

        var position: Int = 0
        arguments?.let {
            position = ShowNoteFragmentArgs.fromBundle(it).noteID as Int
        }
        //val position = arguments?.getInt(KeyValues.NotePositionBundle.key) as Int

        val noteViewModel = (activity as FragmentActivity).getNoteViewModel()
        val note = noteViewModel.getSpecificNote(position)

        binding.textView.text = note.title
        binding.editTextText.text = note.content

        binding.buttonCancel.setOnClickListener {
            returnToMainMenu(it) }

        binding.buttonEdit.setOnClickListener {
            val action = ShowNoteFragmentDirections.actionShowNoteFragmentToEditNoteFragment(position)
            Navigation.findNavController(it).navigate(action)

            /*
            val notePosition = Bundle()
            notePosition.putInt(KeyValues.NotePositionBundle.key, position)

            val editNoteFragment = EditNoteFragment()
            editNoteFragment.arguments = notePosition
            val fragmentTransaction: FragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()

            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            fragmentTransaction.replace(R.id.frameLayout, editNoteFragment)

            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
            */

        }


        return view
    }

    private fun returnToMainMenu(view: View) {
        val action = ShowNoteFragmentDirections.actionShowNoteFragmentToMainMenuFragment()
        Navigation.findNavController(view).navigate(action)

        /*
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