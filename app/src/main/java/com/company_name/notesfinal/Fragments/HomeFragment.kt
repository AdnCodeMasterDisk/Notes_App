package com.company_name.notesfinal.Fragments

import android.app.Activity
import android.content.DialogInterface
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.TextUtils.replace
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.company_name.notesfinal.Adapter.NotesAdapter
import com.company_name.notesfinal.Adapter.OnClick
import com.company_name.notesfinal.MainActivity
import com.company_name.notesfinal.Model.NotesEntity
import com.company_name.notesfinal.R
import com.company_name.notesfinal.ViewModel.NotesViewModel
import com.company_name.notesfinal.databinding.FragmentHomeBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
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

        //search a note
        binding.searchBtn.setOnClickListener {

            if (binding.searchTextHeading.text.isNotEmpty()) {
                Toast.makeText(requireContext(), "Searching...", Toast.LENGTH_SHORT).show()
                viewModel.searchNote(binding.searchTextHeading.text.toString())
                    .observe(viewLifecycleOwner, Observer { notesList ->

                        val staggeredGridLayoutManager =
                            StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
                        binding.recyclerView.layoutManager = staggeredGridLayoutManager

                        val adapter = NotesAdapter(requireContext(), notesList, this)
                        binding.recyclerView.adapter = adapter

                    })
            } else {
                Toast.makeText(requireContext(), "Nothing to search", Toast.LENGTH_SHORT).show()
            }
        }

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

