package com.pratyakshkhurana.notes

import android.annotation.SuppressLint
import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import com.pratyakshkhurana.notes.Data_or_Model.NotesEntity
import com.pratyakshkhurana.notes.ViewModel.NotesViewModel
import kotlinx.android.synthetic.main.activity_edit_note.*
import java.time.LocalTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

class EditNote : AppCompatActivity() {

    private var priorityDefault: String = "1" //high
    private val coloursOfNotes = arrayListOf("#ffcc80", "#fbaa93", "#e7ed9b", "#81deea", "#cf94da")
    private val viewModel: NotesViewModel by viewModels()

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_note)


        title_act_EditNote.setText(intent.getStringExtra("t").toString())

        if (intent.getStringExtra("p").toString() == "1") {
            priority_Edit_note.text = "High"
        } else if (intent.getStringExtra("p").toString() == "2") {
            priority_Edit_note.text = "Medium"
        } else if (intent.getStringExtra("p").toString() == "3") {
            priority_Edit_note.text = "Low"
        }
        description_act_EditNote.setText(intent.getStringExtra("d"))



        backBtn_CreateNote.setOnClickListener {
//            if (title.isNotEmpty()) {
//                val dialog = AlertDialog.Builder(this)
//                    .setTitle("Alert")
//                    .setMessage("Do you wish to save the notes ?")
//                    .setCancelable(false)
//                    .setNegativeButton("NO"){ _, _ ->
//
//                    }
//                    .setPositiveButton("YES"){ _,_ ->
//                        Toast.makeText(this,"Click the save icon",Toast.LENGTH_LONG).show()
//                    }
//                dialog.show()
//            } else
            startActivity(Intent(this, MainActivity::class.java))
        }

        shareBtn_CreateNote.setOnClickListener {
            if (title_act_EditNote.text.toString().isNotEmpty()) {
                val intent = Intent(Intent.ACTION_SEND)
                intent.type = "text/plain"
                intent.putExtra(
                    Intent.EXTRA_TEXT,
                    "${title_act_EditNote.text}, ${description_act_EditNote.text}"
                )

                //chooser where to send and it will display all apps capable of sharing in android device
                val chooser = Intent.createChooser(intent, "Share this using, ")
                startActivity(chooser)
            } else {
                Toast.makeText(this, "Add a title", Toast.LENGTH_SHORT).show()
            }
        }
    }
}