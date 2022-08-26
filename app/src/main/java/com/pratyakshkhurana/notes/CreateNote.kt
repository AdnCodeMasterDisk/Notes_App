package com.pratyakshkhurana.notes

import android.annotation.SuppressLint
import android.icu.text.DateFormat.getDateInstance
import android.icu.text.SimpleDateFormat
import android.os.Build
import android.os.Bundle
import android.text.format.DateFormat
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.pratyakshkhurana.notes.Data_or_Model.NotesEntity
import com.pratyakshkhurana.notes.ViewModel.NotesViewModel
import kotlinx.android.synthetic.main.activity_create_note.*
import java.text.DateFormat.getDateInstance
import java.time.LocalTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*


class CreateNote : AppCompatActivity() {

    private var priorityDefault: String = "1" //high
    private val viewModel: NotesViewModel by viewModels()

    @SuppressLint("SimpleDateFormat")
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

            val title = title_act_CreateNote.text.toString()
            val desc = description_act_CreateNote.text.toString()
            val priority = priorityDefault
            Toast.makeText(this, "$title $desc $priority", Toast.LENGTH_SHORT).show()

            val time: LocalTime = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                LocalTime.now(ZoneId.systemDefault())
            } else {
                TODO("VERSION.SDK_INT < O")
            }

            val dtf: DateTimeFormatter = DateTimeFormatter.ofPattern("hh:mm a", Locale.ENGLISH)
            val currentTime = dtf.format(time) // Alternatively, time.format(dtf)

            Log.e("@", currentTime)

            val currentDate: String =
                SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(Date()).toString()
            Toast.makeText(this, currentDate, Toast.LENGTH_SHORT).show()


            val obj = NotesEntity(
                null,
                title = title,
                description = desc,
                lastUpdatedTime = currentTime,
                lastUpdatedDate = currentDate,
                priority = priorityDefault
            )
            viewModel.insert(obj)

            finish()
        }

    }
}