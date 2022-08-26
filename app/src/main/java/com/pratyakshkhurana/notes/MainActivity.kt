package com.pratyakshkhurana.notes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
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


    }

    private fun createNote(it: View?) {
        when (it) {
            floatingAddButton -> {
                startActivity(Intent(this, CreateNote::class.java))
            }
        }
    }
}