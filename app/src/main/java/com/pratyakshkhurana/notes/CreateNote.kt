package com.pratyakshkhurana.notes

import android.icu.text.SimpleDateFormat
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.pratyakshkhurana.notes.Data_or_Model.NotesEntity
import com.pratyakshkhurana.notes.ViewModel.NotesViewModel
import kotlinx.android.synthetic.main.activity_create_note.*
import java.util.*

class CreateNote : AppCompatActivity() {

    private var priorityDefault: String = "1" //high
    private val viewModel: NotesViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_note)


        high_create_note.setOnClickListener {
            high_create_note.setBackgroundResource(R.drawable.on_click_priority_create_note)
            low_create_note.setBackgroundResource(R.drawable.search_bg)
            medium_create_note.setBackgroundResource(R.drawable.search_bg)
            priorityDefault = "1"
        }

        low_create_note.setOnClickListener {
            high_create_note.setBackgroundResource(R.drawable.search_bg)
            low_create_note.setBackgroundResource(R.drawable.on_click_priority_create_note)
            medium_create_note.setBackgroundResource(R.drawable.search_bg)
            priorityDefault = "2"
        }

        medium_create_note.setOnClickListener {
            high_create_note.setBackgroundResource(R.drawable.search_bg)
            low_create_note.setBackgroundResource(R.drawable.search_bg)
            medium_create_note.setBackgroundResource(R.drawable.on_click_priority_create_note)
            priorityDefault = "3"
        }

        saveFloatingButton_create_note.setOnClickListener {

            val title = title_act_CreateNote.toString()
            val desc = description_act_CreateNote.toString()
            val priority = priorityDefault
//            val currentTime: String = Calendar.getInstance().time.toString()
//            Log.e("@@", currentTime)

            val currentTime: String = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(Date()).toString()
            Toast.makeText(this, currentTime, Toast.LENGTH_SHORT).show()

            val obj = NotesEntity(
                null,
                title = title,
                description = desc,
                lastUpdatedTime = currentTime,
                priority = priorityDefault
            )

        }

    }
}