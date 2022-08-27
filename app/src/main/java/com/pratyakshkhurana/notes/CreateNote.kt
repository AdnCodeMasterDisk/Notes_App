package com.pratyakshkhurana.notes

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.icu.text.DateFormat.getDateInstance
import android.icu.text.SimpleDateFormat
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
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
    private val coloursOfNotes = arrayListOf("#ffcc80", "#fbaa93", "#e7ed9b", "#81deea", "#cf94da")

    @SuppressLint("SimpleDateFormat")
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_note)

        high_create_note.setBackgroundResource(R.drawable.on_click_priority_create_note)

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
            Log.e("@@", currentDate) //27-08-2022

            val date = (currentDate[0].toString() + currentDate[1].toString())
            val month = (currentDate[3].toString() + currentDate[4].toString())
            val year =
                (currentDate[6].toString() + currentDate[7].toString() + currentDate[8].toString() + currentDate[9].toString())

//            val requiredDateFormat
            var requiredDateFormat = ""
            when (month) {
                "01" -> requiredDateFormat += "Jan"
                "02" -> requiredDateFormat += "Feb"
                "03" -> requiredDateFormat += "Mar"
                "04" -> requiredDateFormat += "April"
                "05" -> requiredDateFormat += "May"
                "06" -> requiredDateFormat += "June"
                "07" -> requiredDateFormat += "July"
                "08" -> requiredDateFormat += "Aug"
                "09" -> requiredDateFormat += "Sept"
                "10" -> requiredDateFormat += "Oct"
                "11" -> requiredDateFormat += "Nov"
                "12" -> requiredDateFormat += "Dec"
            }
            requiredDateFormat += " $date, $year"
            Log.e("123", requiredDateFormat) //27-08-2022

            val obj = NotesEntity(
                null,
                title = title,
                description = desc,
                lastUpdatedTime = currentTime,
                lastUpdatedDate = currentDate,
                priority = priority,
                noteColourBackground = coloursOfNotes[(0..4).random()],
                requiredDateFormat = requiredDateFormat
            )

            if (title.isNotEmpty()) {
                viewModel.insert(obj)
                Toast.makeText(this, "Last updated : $currentTime", Toast.LENGTH_LONG).show()

                //delay to view toast
                Handler(Looper.getMainLooper()).postDelayed(Runnable {
                    finish()
                }, 2000)
            } else {
                Toast.makeText(this, "No title", Toast.LENGTH_LONG).show()
            }
        }

    }
}