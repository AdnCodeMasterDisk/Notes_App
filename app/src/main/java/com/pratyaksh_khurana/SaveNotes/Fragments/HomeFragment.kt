package com.pratyaksh_khurana.SaveNotes.Fragments

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.pratyaksh_khurana.SaveNotes.Adapter.NotesAdapter
import com.pratyaksh_khurana.SaveNotes.Adapter.OnClick
import com.pratyaksh_khurana.SaveNotes.Model.NotesEntity
import com.pratyaksh_khurana.SaveNotes.R
import com.pratyaksh_khurana.SaveNotes.ViewModel.NotesViewModel
import com.pratyaksh_khurana.SaveNotes.databinding.FragmentHomeBinding
import kotlin.system.exitProcess


class HomeFragment : Fragment(), OnClick {
    //enable view binding for fragment
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: NotesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        //by default all notes are visible
        binding.all.setBackgroundResource(R.drawable.bg_on_click)
        viewModel.getAllNotes().observe(viewLifecycleOwner) { notesList ->
            val staggeredGridLayoutManager =
                StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
            binding.recyclerView.layoutManager = staggeredGridLayoutManager
            binding.recyclerView.adapter =
                NotesAdapter(requireContext(), notesList, this)

        }

        //exit app
        binding.exitBtn.setOnClickListener {
            android.app.AlertDialog.Builder(requireContext())
                .setTitle("Confirm Exit !")
                .setIcon(R.drawable.exit_icon)
                .setMessage("Are you sure you want to exit ?")
                .setCancelable(false)
                .setNegativeButton("NO") { _, _ ->
                }
                .setPositiveButton("YES") { _: DialogInterface, _: Int ->
                    exitProcess(0)
                }.show()
        }

        //add a note
        binding.floatingAddButton.setOnClickListener {
            //navigating through fragments
            fragmentManager?.beginTransaction()?.apply {
                replace(R.id.fragmentContainer, CreateNoteFragment())
                    .commit()
            }
        }

        binding.searchTextHeading.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                if (binding.searchTextHeading.text.isEmpty()) {
                    Toast.makeText(requireContext(), "Nothing to search", Toast.LENGTH_SHORT).show()
                } else {
                    viewModel.searchNote(binding.searchTextHeading.text.toString())
                        .observe(viewLifecycleOwner) { notesList ->
                            if (notesList.isEmpty()) {
                                Toast.makeText(
                                    requireContext(),
                                    "No results found",
                                    Toast.LENGTH_SHORT
                                )
                                    .show()
                            } else {
                                binding.all.setBackgroundResource(R.drawable.bg_on_click)
                                binding.medium.setBackgroundResource(R.drawable.bg)
                                binding.high.setBackgroundResource(R.drawable.bg)
                                binding.low.setBackgroundResource(R.drawable.bg)

                                val staggeredGridLayoutManager =
                                    StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
                                binding.recyclerView.layoutManager = staggeredGridLayoutManager

                                val adapter = NotesAdapter(requireContext(), notesList, this)
                                binding.recyclerView.adapter = adapter
                            }
                        }
                }
            }
            true
        }
        //search a note
//        binding.searchBtn.setOnClickListener {
//
//            if (binding.searchTextHeading.text.isNotEmpty()) {
//
//                viewModel.searchNote(binding.searchTextHeading.text.toString())
//                    .observe(viewLifecycleOwner) { notesList ->
//                        if (notesList.isEmpty()) {
//                            Toast.makeText(requireContext(), "No results found", Toast.LENGTH_SHORT)
//                                .show()
//                        } else {
//                            binding.all.setBackgroundResource(R.drawable.bg_on_click)
//                            binding.medium.setBackgroundResource(R.drawable.bg)
//                            binding.high.setBackgroundResource(R.drawable.bg)
//                            binding.low.setBackgroundResource(R.drawable.bg)
//
//                            val staggeredGridLayoutManager =
//                                StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
//                            binding.recyclerView.layoutManager = staggeredGridLayoutManager
//
//                            val adapter = NotesAdapter(requireContext(), notesList, this)
//                            binding.recyclerView.adapter = adapter
//                        }
//                    }
//            } else {
//                Toast.makeText(requireContext(), "Nothing to search", Toast.LENGTH_SHORT).show()
//            }
//        }

        //get all notes
        binding.all.setOnClickListener {
            binding.all.setBackgroundResource(R.drawable.bg_on_click)
            binding.medium.setBackgroundResource(R.drawable.bg)
            binding.high.setBackgroundResource(R.drawable.bg)
            binding.low.setBackgroundResource(R.drawable.bg)

            viewModel.getAllNotes().observe(viewLifecycleOwner, Observer { notesList ->

                val staggeredGridLayoutManager =
                    StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
                binding.recyclerView.layoutManager = staggeredGridLayoutManager

                val adapter = NotesAdapter(requireContext(), notesList, this)
                binding.recyclerView.adapter = adapter

            })
        }

        //get notes of high priority
        binding.high.setOnClickListener {
            binding.high.setBackgroundResource(R.drawable.bg_on_click)
            binding.medium.setBackgroundResource(R.drawable.bg)
            binding.low.setBackgroundResource(R.drawable.bg)
            binding.all.setBackgroundResource(R.drawable.bg)

            viewModel.getHighPriorityNotes().observe(viewLifecycleOwner) { notesList ->

                val staggeredGridLayoutManager =
                    StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
                binding.recyclerView.layoutManager = staggeredGridLayoutManager

                val adapter = NotesAdapter(requireContext(), notesList, this)
                binding.recyclerView.adapter = adapter

            }
        }

        //get notes of medium priority
        binding.medium.setOnClickListener {
            binding.medium.setBackgroundResource(R.drawable.bg_on_click)
            binding.low.setBackgroundResource(R.drawable.bg)
            binding.high.setBackgroundResource(R.drawable.bg)
            binding.all.setBackgroundResource(R.drawable.bg)


            viewModel.getMediumPriorityNotes().observe(viewLifecycleOwner) { notesList ->
                val staggeredGridLayoutManager =
                    StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
                binding.recyclerView.layoutManager = staggeredGridLayoutManager

                val adapter = NotesAdapter(requireContext(), notesList, this)
                binding.recyclerView.adapter = adapter
            }
        }

        //get notes of low priority
        binding.low.setOnClickListener {
            binding.low.setBackgroundResource(R.drawable.bg_on_click)
            binding.medium.setBackgroundResource(R.drawable.bg)
            binding.high.setBackgroundResource(R.drawable.bg)
            binding.all.setBackgroundResource(R.drawable.bg)

            viewModel.getLowPriorityNotes().observe(viewLifecycleOwner, Observer { notesList ->

                val staggeredGridLayoutManager =
                    StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
                binding.recyclerView.layoutManager = staggeredGridLayoutManager

                val adapter = NotesAdapter(requireContext(), notesList, this)
                binding.recyclerView.adapter = adapter

            })
        }
        return binding.root
    }


    //listener made in adapter when view is clicked in recycler view
    override fun onClick(note: NotesEntity) {

        //data is passed between fragments in the form of bundles
        val bundle = Bundle()

        bundle.putString("title", note.title)
        note.id?.let { bundle.putInt("id", it) }
        bundle.putString("description", note.description)
        bundle.putString("priority", note.priority)
        bundle.putString("notesColourBackground", note.noteColourBackground)

        //set destination
        val fragment = EditNoteFragment()
        fragment.arguments = bundle

        //use fragment manager
        fragmentManager?.beginTransaction()?.replace(R.id.fragmentContainer, fragment)
            ?.commit()
    }

    //listener made in adapter when view is clicked in recycler view
    override fun deleteNote(note: NotesEntity) {
        viewModel.deleteNote(note)
    }

}

