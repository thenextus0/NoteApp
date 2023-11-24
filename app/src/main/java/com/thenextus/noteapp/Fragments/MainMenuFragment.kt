package com.thenextus.noteapp.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import com.thenextus.noteapp.Classes.Database.NotesDAO
import com.thenextus.noteapp.Classes.KeyValues
import com.thenextus.noteapp.Classes.Note
import com.thenextus.noteapp.Classes.NoteRecyclerViewAdapter
import com.thenextus.noteapp.FragmentActivity
import com.thenextus.noteapp.OldActivities.MainActivity
import com.thenextus.noteapp.R
import com.thenextus.noteapp.databinding.FragmentMainMenuBinding

class MainMenuFragment : Fragment(), NoteRecyclerViewAdapter.OnDeleteClickListener {

    private var _binding: FragmentMainMenuBinding? = null
    private val binding get() = _binding!!
    lateinit var noteList: ArrayList<Note>


    private lateinit var noteRecyclerViewAdapter: NoteRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentMainMenuBinding.inflate(inflater, container, false)
        val view = binding.root

        //val noteViewModel = (activity as FragmentActivity).getNoteViewModel()
        val noteViewModel = (activity as FragmentActivity).getNoteViewModel()
        noteList = noteViewModel.getNotes()

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        noteRecyclerViewAdapter = NoteRecyclerViewAdapter(noteList, object : NoteRecyclerViewAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {

                val notePosition = Bundle()
                notePosition.putInt(KeyValues.NotePositionBundle.key, position)

                val showNoteFragment = ShowNoteFragment()
                showNoteFragment.arguments = notePosition
                val fragmentTransaction: FragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()

                fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                fragmentTransaction.replace(R.id.frameLayout, showNoteFragment)
                fragmentTransaction.addToBackStack(null)
                fragmentTransaction.commit()
            }
        })

        noteRecyclerViewAdapter.setOnDeleteClickListener(this)
        binding.recyclerView.adapter = noteRecyclerViewAdapter

        binding.button2.setOnClickListener {
            val newNoteFragment = NewNoteFragment()
            val fragmentTransaction: FragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()

            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            fragmentTransaction.replace(R.id.frameLayout, newNoteFragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDeleteClick(position: Int) {
        val notesDAO = NotesDAO(requireActivity())
        notesDAO.deleteNoteByID(noteList[position].noteID)
        noteList.removeAt(position)

        noteRecyclerViewAdapter.notifyDataSetChanged()
    }
}