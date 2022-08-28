package com.pratyakshkhurana.notes

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.pratyakshkhurana.notes.ViewModel.NotesViewModel
import kotlinx.android.synthetic.main.activity_edit_note.*

class EditNote : AppCompatActivity() {

    private var priorityDefault: String = "1" //high
    private val coloursOfNotes = arrayListOf("#ffcc80", "#fbaa93", "#e7ed9b", "#81deea", "#cf94da")
    private val viewModel: NotesViewModel by viewModels()

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_note)



        title_act_EditNote.setText(intent.getStringExtra("t")).toString()
        Toast.makeText(this,title_act_EditNote.text.toString(),Toast.LENGTH_LONG).show()
        description_act_EditNote.setText(intent.getStringExtra("d")).toString()
        priority_Edit_note.setBackgroundResource(R.drawable.bg_on_click).toString()
        currDate_EditNote.text = intent.getStringExtra("r").toString()

        saveFloatingButton_edit_note.setOnClickListener {
            val z :Int= 12
            val i = Intent(this, MainActivity::class.java)
            i.putExtra("tt", title_act_EditNote.text.toString())
            i.putExtra("dd", description_act_EditNote.text.toString())
            i.putExtra("rr", currDate_EditNote.text.toString())
            i.putExtra("pp", priority_Edit_note.text.toString())
            i.putExtra("bb", z)

            startActivity(i)
        }

        when (intent.getStringExtra("p")) {
            "1" -> priority_Edit_note.setText("High").toString()
            "2" -> priority_Edit_note.setText("Medium").toString()
            "3" -> priority_Edit_note.setText("Low").toString()
        }


//        saveFloatingButton_create_note?.setOnClickListener {
//
//            val title = title_act_CreateNote.text.toString()
//            val desc = description_act_CreateNote.text.toString()
//            val priority = priorityDefault
//
//            val time: LocalTime = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                LocalTime.now(ZoneId.systemDefault())
//            } else {
//                TODO("VERSION.SDK_INT < O")
//            }
//
//            val dtf: DateTimeFormatter = DateTimeFormatter.ofPattern("hh:mm a", Locale.ENGLISH)
//            val currentTime = dtf.format(time) // Alternatively, time.format(dtf)
//
//            Log.e("@", currentTime)
//
//            val currentDate: String =
//                SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(Date()).toString()
//            Log.e("@@", currentDate) //27-08-2022
//
//            val date = (currentDate[0].toString() + currentDate[1].toString())
//            val month = (currentDate[3].toString() + currentDate[4].toString())
//            val year =
//                (currentDate[6].toString() + currentDate[7].toString() + currentDate[8].toString() + currentDate[9].toString())
//
////            val requiredDateFormat
//            var requiredDateFormat = ""
//            when (month) {
//                "01" -> requiredDateFormat += "Jan"
//                "02" -> requiredDateFormat += "Feb"
//                "03" -> requiredDateFormat += "Mar"
//                "04" -> requiredDateFormat += "April"
//                "05" -> requiredDateFormat += "May"
//                "06" -> requiredDateFormat += "June"
//                "07" -> requiredDateFormat += "July"
//                "08" -> requiredDateFormat += "Aug"
//                "09" -> requiredDateFormat += "Sept"
//                "10" -> requiredDateFormat += "Oct"
//                "11" -> requiredDateFormat += "Nov"
//                "12" -> requiredDateFormat += "Dec"
//            }
//            requiredDateFormat += " $date, $year"
//            Log.e("123", requiredDateFormat) //27-08-2022
//
//            val obj = NotesEntity(
//                title = title,
//                description = desc,
//                lastUpdatedTime = currentTime,
//                lastUpdatedDate = currentDate,
//                priority = priority,
//                noteColourBackground = coloursOfNotes[(0..4).random()],
//                requiredDateFormat = requiredDateFormat
//            )
//
//            if (title.isNotEmpty()) {
//
//                currDate_CreateNote.text = requiredDateFormat
//
////                Toast.makeText(this, "Saved, last updated : $currentTime", Toast.LENGTH_LONG).show()
//
//                //delay to view toast (REMOVED TO ENSURE USER CAN SHARE IF THEY NEED TO AFTER SAVING THE NOTE)
////                Handler(Looper.getMainLooper()).postDelayed({
////                    finish()
////                }, 2000)
//            } else {
//                Toast.makeText(this, "Add a title", Toast.LENGTH_LONG).show()
//            }
//        }

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
