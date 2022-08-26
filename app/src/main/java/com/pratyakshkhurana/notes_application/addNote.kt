package com.pratyakshkhurana.notes_application

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.pratyakshkhurana.notes_application.Model.Notes
import com.pratyakshkhurana.notes_application.ViewModel.NotesViewModel
import kotlinx.android.synthetic.main.activity_add_note.*
import java.text.SimpleDateFormat
import java.util.*

class addNote : AppCompatActivity(), View.OnClickListener {

    //initially 1
    private var priority = "1"
    private val viewModel: NotesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)
        Toast.makeText(this, "open", Toast.LENGTH_SHORT).show()


//        val x = intent.getStringExtra("title").toString()
//        val y = intent.getStringExtra("subtitle").toString()
//        if(x.isNotEmpty())
//        Title.setText(intent.getStringExtra("title").toString())
//        if(y.isNotEmpty())
//        subTitle.setText(intent.getStringExtra("subtitle").toString())


        btnTickAddNote.setOnClickListener(this)
        ivPriority1.setOnClickListener(this)
        ivPriority2.setOnClickListener(this)
        ivPriority3.setOnClickListener(this)

    }

    override fun onClick(it: View?) {
        when (it) {
            ivPriority1 -> {
                ivPriority2.setImageResource(R.drawable.background_priority2_default)
                ivPriority3.setImageResource(R.drawable.background_priority3_default)
                ivPriority1.setImageResource(R.drawable.background_priority1_clicked)
                priority = "1"
            }
            ivPriority2 -> {
                ivPriority2.setImageResource(R.drawable.background_priority2_clicked)
                ivPriority3.setImageResource(R.drawable.background_priority3_default)
                ivPriority1.setImageResource(R.drawable.background_priority1_default)
                priority = "2"
            }
            ivPriority3 -> {
                ivPriority2.setImageResource(R.drawable.background_priority2_default)
                ivPriority3.setImageResource(R.drawable.background_priority3_clicked)
                ivPriority1.setImageResource(R.drawable.background_priority1_default)
                priority = "3"
            }
            btnTickAddNote -> {
                createNote()
            }
        }
    }

    private fun createNote() {
        defaultBackgrounds()
        //store data in database
        val noteTitle = Title.text.toString()
        val noteSubTitle = subTitle.text.toString()
        val notesText = noteText.text.toString()

        //current date
        val notesDate =
            SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(Date()).toString()
        //check in logs
        Log.e("@@@@@", noteTitle)
        Toast.makeText(this, noteTitle, Toast.LENGTH_SHORT).show()
        //id set to null as it will generate automatically

        val notesData = Notes(
            null, title = noteTitle,
            subTitle = noteSubTitle,
            notes = notesText,
            date = notesDate,
            priority = priority
        )

        viewModel.addNotes(notesData)
        Toast.makeText(this, "note created", Toast.LENGTH_SHORT).show()
        //all notes show up in main activity in recycler view
        startActivity(Intent(this, MainActivity::class.java))
    }

    private fun defaultBackgrounds() {
        ivPriority2.setImageResource(R.drawable.background_priority2_default)
        ivPriority3.setImageResource(R.drawable.background_priority3_default)
        ivPriority1.setImageResource(R.drawable.background_priority1_default)
    }

    fun onClick(note: Notes) {

    }

}