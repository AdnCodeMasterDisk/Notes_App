package com.pratyakshkhurana.notes

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.HandlerCompat.postDelayed
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.pratyakshkhurana.notes.Adapter.NotesAdapter
import com.pratyakshkhurana.notes.Adapter.OnClickCardView
import com.pratyakshkhurana.notes.Data_or_Model.NotesEntity
import com.pratyakshkhurana.notes.ViewModel.NotesViewModel
import kotlinx.android.synthetic.main.activity_main.*


//MVVM - used to follow a clean structure
//Three layers - UI , business logic, Data
class MainActivity : AppCompatActivity(), OnClickCardView {

    private val viewModel: NotesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val x = intent.getStringExtra("tt").toString()
        Toast.makeText(this, "x = $x", Toast.LENGTH_SHORT).show()


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
    }

    //Listener for share and delete
    override fun onClick(
        title: String,
        desc: String,
        requiredDateFormat: String,
        priority: String
    ) {
        val intent = Intent(this, EditNote::class.java)
        intent.putExtra("t", title)
        intent.putExtra("d", desc)
        intent.putExtra("r", requiredDateFormat)
        intent.putExtra("p", priority)
        startActivity(intent)
    }

    override fun del(note: NotesEntity) {
        viewModel.delete(note)
        viewModel.getAllNotes().observe(this, Observer { l ->
            run {

                val staggeredGridLayoutManager =
                    StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
                recyclerView.layoutManager = staggeredGridLayoutManager

//                recyclerView.layoutManager = GridLayoutManager(this, 2)
                val adapter = NotesAdapter(this, l, this)
                recyclerView.adapter = adapter
//                adapter.updateList(l)
            }
        })
    }

}