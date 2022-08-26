package com.pratyakshkhurana.notes

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.pratyakshkhurana.notes.Adapter.NotesAdapter
import com.pratyakshkhurana.notes.ViewModel.NotesViewModel
import kotlinx.android.synthetic.main.activity_main.*


//MVVM - used to follow a clean structure
//Three layers - UI , business logic, Data
class MainActivity : AppCompatActivity() {

    private val viewModel: NotesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        floatingAddButton.setOnClickListener {
            createNote(it)
        }


        viewModel.getAllNotes().observe(this, Observer {
            run {

                val staggeredGridLayoutManager =
                    StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
                recyclerView.layoutManager = staggeredGridLayoutManager

//                recyclerView.layoutManager = GridLayoutManager(this, 2)
                recyclerView.adapter = NotesAdapter(this, it)

            }
        })

    }

    private fun createNote(it: View?) {
        when (it) {
            floatingAddButton -> {
                startActivity(Intent(this, CreateNote::class.java))
            }
        }
    }
}