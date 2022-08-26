package com.pratyakshkhurana.notes_application

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.pratyakshkhurana.notes_application.Adapter.NotesAdapter
import com.pratyakshkhurana.notes_application.Adapter.OnClickView
import com.pratyakshkhurana.notes_application.Model.Notes
import com.pratyakshkhurana.notes_application.ViewModel.NotesViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), OnClickView {

    private val viewModel: NotesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        btnAddNote.setOnClickListener {
            startActivity(Intent(this, addNote::class.java))
        }

        yellow.setOnClickListener{
            viewModel.getYellowNotes().observe(this, Observer { notesList ->
                for (i in notesList) {
                    Log.e("@", i.title)
                }

                //for 2 columns
                recyclerView.layoutManager = LinearLayoutManager(this)
                recyclerView.adapter = NotesAdapter(this, notesList, this)
            })
        }

        red.setOnClickListener{
            viewModel.getRedNotes().observe(this, Observer { notesList ->
                for (i in notesList) {
                    Log.e("@", i.title)
                }

                //for 2 columns
                recyclerView.layoutManager = LinearLayoutManager(this)
                recyclerView.adapter = NotesAdapter(this, notesList, this)
            })
        }

        green.setOnClickListener{
            viewModel.getGreenNotes().observe(this, Observer { notesList ->
                for (i in notesList) {
                    Log.e("@", i.title)
                }

                //for 2 columns
                recyclerView.layoutManager = LinearLayoutManager(this)
                recyclerView.adapter = NotesAdapter(this, notesList, this)
            })
        }


        //fetch all notes from viewModel as live data, then observe as it is live data
        viewModel.getNotes().observe(this, Observer { notesList ->
            for (i in notesList) {
                Log.e("@", i.title)
            }

            //for 2 columns
            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.adapter = NotesAdapter(this, notesList, this)
        })

    }

    override fun onClick(note: Notes) {

        //TODO
//        val intent = Intent(this, addNote::class.java)
//        intent.putExtra("title", note.title)
//        intent.putExtra("subtitle", note.subTitle)
//        intent.putExtra("priority", note.priority)
//        intent.putExtra("notesText", note.notes)
//        startActivity(intent)

    }


}