package com.pratyakshkhurana.notes

import android.annotation.SuppressLint
import android.content.Intent
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
import kotlinx.android.synthetic.main.activity_create_note.backBtn_CreateNote
import kotlinx.android.synthetic.main.activity_create_note.shareBtn_CreateNote
import java.time.LocalTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*


class CreateNote : AppCompatActivity() {

    private var priorityDefault: String = "1" //high
    private val viewModel: NotesViewModel by viewModels()
    private val coloursOfNotes =
        arrayListOf("#fd99ff", "#ff9e9e", "#9effff", "#fff599", "#91f48f", "#b69cff")

    @SuppressLint("SimpleDateFormat")
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_note)


        viewModel.insert(
            NotesEntity(
                null,
                "Awesome tweets collection",
                "desc",
                "labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
                "Feb 20, 2022",
                "1",
                "#fd99ff",
                "Feb 20, 2022"
            )
        )

        viewModel.insert(
            NotesEntity(
                null,
                "UI concepts worth existing",
                "x ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
                "hjvbb",
                "Feb 20, 2022",
                "1",
                "#ff9e9e",
                "Feb 20, 2022"
            )
        )

        viewModel.insert(
            NotesEntity(
                null,
                "Book review: the design of everyday things by Don Norman",
                "enim ipsam voluptatem quia volu",
                "hjvbb",
                "Feb 20, 2022",
                "1",
                "#fff599",
                "Feb 20, 2022"
            )
        )

        viewModel.insert(
            NotesEntity(
                null,
                "Animes produces by Ufotable",
                "d I will give you a complete account of the system, and expound the actual teachings of the great explorer of the truth, the master-",
                "hjvbb",
                "Feb 20, 2022",
                "1",
                "#91f48f",
                "Feb 20, 2022"
            )
        )

        viewModel.insert(
            NotesEntity(
                null,
                "Mangas planned to be read",
                "imilique sunt in culpa qui officia deserunt mollitia animi, id est laborum et dolorum fuga. Et harum quidem rerum facilis est et expedita distinctio. Nam libero tempore, cum soluta nobis est eligendi optio cumque nihil impedit quo minus id quod maxime placeat facere possimus, omnis voluptas assumenda est, omnis dolor r",
                "hjvbb",
                "Feb 20, 2022",
                "1",
                "#b69cff",
                "Feb 20, 2022"
            )
        )

        priority_create_note.setBackgroundResource(R.drawable.bg_on_click)
        currDate_CreateNote.text = dat()

        priority_create_note.setOnClickListener {
            priority_create_note.setBackgroundResource(R.drawable.bg_on_click)
            low_create_note.setBackgroundResource(R.drawable.bg)
            medium_create_note.setBackgroundResource(R.drawable.bg)
            priorityDefault = "1"
        }

        low_create_note.setOnClickListener {
            priority_create_note.setBackgroundResource(R.drawable.bg)
            low_create_note.setBackgroundResource(R.drawable.bg_on_click)
            medium_create_note.setBackgroundResource(R.drawable.bg)
            priorityDefault = "3"
        }

        medium_create_note.setOnClickListener {
            priority_create_note.setBackgroundResource(R.drawable.bg)
            low_create_note.setBackgroundResource(R.drawable.bg)
            medium_create_note.setBackgroundResource(R.drawable.bg_on_click)
            priorityDefault = "2"
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
                noteColourBackground = coloursOfNotes[(0..5).random()],
                requiredDateFormat = requiredDateFormat
            )

            if (title.isNotEmpty()) {
                viewModel.insert(obj)
                Toast.makeText(this, "Last updated : $currentTime", Toast.LENGTH_LONG).show()

//                //delay to view toast
//                Handler(Looper.getMainLooper()).postDelayed(Runnable {
//                    finish()
//                }, 2000)
            } else {
                Toast.makeText(this, "No title", Toast.LENGTH_LONG).show()
            }
        }

        backBtn_CreateNote.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        shareBtn_CreateNote.setOnClickListener {
            if (title_act_CreateNote.text.toString().isNotEmpty()) {
                val intent = Intent(Intent.ACTION_SEND)
                intent.type = "text/plain"
                intent.putExtra(
                    Intent.EXTRA_TEXT,
                    "${title_act_CreateNote.text}, ${description_act_CreateNote.text}"
                )

                //chooser where to send and it will display all apps capable of sharing in android device
                val chooser = Intent.createChooser(intent, "Share this using, ")
                startActivity(chooser)
            } else {
                Toast.makeText(this, "Add a title", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun dat(): String {
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
            currentDate[6].toString() + currentDate[7].toString() + currentDate[8].toString() + currentDate[9].toString()

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
        return requiredDateFormat
    }
}