package com.pratyakshkhurana.notes

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.pratyakshkhurana.notes.Adapter.NotesAdapter
import com.pratyakshkhurana.notes.Adapter.OnClickCardView
import com.pratyakshkhurana.notes.Data_or_Model.NotesEntity
import com.pratyakshkhurana.notes.ViewModel.NotesViewModel
import kotlinx.android.synthetic.main.activity_main.*
import java.io.Serializable


//MVVM - used to follow a clean structure
//Three layers - UI , business logic, Data
class MainActivity : AppCompatActivity(), OnClickCardView {

    private val viewModel: NotesViewModel by viewModels()
    private lateinit var clickedObj: NotesEntity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //by default all notes are visible on screen
        all.setBackgroundResource(R.drawable.bg_on_click)

        floatingAddButton.setOnClickListener {
            startActivity(Intent(this, CreateNote::class.java))
        }

        //view model perform query indirectly and it observes and update the recycler view
        searchBtn.setOnClickListener {

            if (searchTextHeading.text.isNotEmpty()) {
                Toast.makeText(this, "Searching...", Toast.LENGTH_SHORT).show()
                viewModel.search(searchTextHeading.text.toString())
                    .observe(this, Observer { notesList ->
                        run {
                            val staggeredGridLayoutManager =
                                StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
                            recyclerView.layoutManager = staggeredGridLayoutManager

                            val adapter = NotesAdapter(this, notesList, this)
                            recyclerView.adapter = adapter
                        }
                    })
            } else {
                Toast.makeText(this, "Nothing to search", Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.getAllNotes().observe(this, Observer { notesList ->
            run {
                val staggeredGridLayoutManager =
                    StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
                recyclerView.layoutManager = staggeredGridLayoutManager

                val adapter = NotesAdapter(this, notesList, this)
                recyclerView.adapter = adapter
            }
        })

        all.setOnClickListener {
            all.setBackgroundResource(R.drawable.bg_on_click)
            medium.setBackgroundResource(R.drawable.bg)
            high.setBackgroundResource(R.drawable.bg)
            low.setBackgroundResource(R.drawable.bg)

            viewModel.getAllNotes().observe(this, Observer { notesList ->
                run {
                    val staggeredGridLayoutManager =
                        StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
                    recyclerView.layoutManager = staggeredGridLayoutManager

                    val adapter = NotesAdapter(this, notesList, this)
                    recyclerView.adapter = adapter
                }
            })
        }

        high.setOnClickListener {
            high.setBackgroundResource(R.drawable.bg_on_click)
            medium.setBackgroundResource(R.drawable.bg)
            low.setBackgroundResource(R.drawable.bg)
            all.setBackgroundResource(R.drawable.bg)

            viewModel.getHigh().observe(this, Observer { notesList ->
                run {
                    val staggeredGridLayoutManager =
                        StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
                    recyclerView.layoutManager = staggeredGridLayoutManager

                    val adapter = NotesAdapter(this, notesList, this)
                    recyclerView.adapter = adapter
                }
            })
        }

        medium.setOnClickListener {
            medium.setBackgroundResource(R.drawable.bg_on_click)
            low.setBackgroundResource(R.drawable.bg)
            high.setBackgroundResource(R.drawable.bg)
            all.setBackgroundResource(R.drawable.bg)


            viewModel.getMed().observe(this, Observer { notesList ->
                run {
                    val staggeredGridLayoutManager =
                        StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
                    recyclerView.layoutManager = staggeredGridLayoutManager

                    val adapter = NotesAdapter(this, notesList, this)
                    recyclerView.adapter = adapter
                }
            })
        }

        low.setOnClickListener {
            low.setBackgroundResource(R.drawable.bg_on_click)
            medium.setBackgroundResource(R.drawable.bg)
            high.setBackgroundResource(R.drawable.bg)
            all.setBackgroundResource(R.drawable.bg)

            viewModel.getLow().observe(this, Observer { notesList ->
                run {
                    val staggeredGridLayoutManager =
                        StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
                    recyclerView.layoutManager = staggeredGridLayoutManager

                    val adapter = NotesAdapter(this, notesList, this)
                    recyclerView.adapter = adapter
                }
            })
        }

//        val positiveButtonClick = { dialog: DialogInterface, which: Int ->
//            Toast.makeText(
//                this,
//                "Fds", Toast.LENGTH_SHORT
//            ).show()
//        }
    }


    override fun del(note: NotesEntity) {

        viewModel.delete(note)
        viewModel.getAllNotes().observe(this, Observer { l ->
            run {

                val staggeredGridLayoutManager =
                    StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
                recyclerView.layoutManager = staggeredGridLayoutManager

                val adapter = NotesAdapter(this, l, this)
                recyclerView.adapter = adapter
            }
        })
    }

    //function in interface in NoteAdapter.kt
    override fun viewClickedOnEdit(note: NotesEntity) {
        //global variable created to store that note when clicked
        clickedObj = note
        Toast.makeText(this, note.title, Toast.LENGTH_SHORT).show()
        val intent = Intent(this, EditNote::class.java)
        intent.putExtra("iid", note.id.toString())
        intent.putExtra("t", note.title.toString())
        intent.putExtra("d", note.description.toString())
        intent.putExtra("lut", note.lastUpdatedTime.toString())
        intent.putExtra("lud", note.lastUpdatedDate.toString())
        intent.putExtra("p", note.priority)
        intent.putExtra("ncb", note.noteColourBackground)
        intent.putExtra("rdf", note.requiredDateFormat)

        startActivity(intent)
    }

}