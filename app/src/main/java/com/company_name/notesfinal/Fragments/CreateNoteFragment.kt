package com.company_name.notesfinal.Fragments

import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.company_name.notesfinal.Model.NotesEntity
import com.company_name.notesfinal.R
import com.company_name.notesfinal.ViewModel.NotesViewModel
import com.company_name.notesfinal.databinding.FragmentCreateNoteBinding
import java.time.LocalTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*


class CreateNoteFragment : Fragment() {
    //enable view binding for fragment
    private lateinit var binding: FragmentCreateNoteBinding

    private var priorityDefault: String = "1" //high
    private val viewModel: NotesViewModel by viewModels()
    private val coloursOfNotes =
        arrayListOf("#e2e41d", "#fffbff", "#e5e5e5", "#c4c4c4", "#e2e41d", "#e2e41d")


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        binding = FragmentCreateNoteBinding.inflate(layoutInflater, container, false)

        //initially set to high priority
        binding.highPriorityCreateNote.setBackgroundResource(R.drawable.bg_on_click)

        binding.highPriorityCreateNote.setOnClickListener {
            binding.highPriorityCreateNote.setBackgroundResource(R.drawable.bg_on_click)
            binding.lowCreateNote.setBackgroundResource(R.drawable.bg)
            binding.mediumCreateNote.setBackgroundResource(R.drawable.bg)
            priorityDefault = "1"
        }

        binding.lowCreateNote.setOnClickListener {
            binding.highPriorityCreateNote.setBackgroundResource(R.drawable.bg)
            binding.lowCreateNote.setBackgroundResource(R.drawable.bg_on_click)
            binding.mediumCreateNote.setBackgroundResource(R.drawable.bg)
            priorityDefault = "3"
        }

        binding.mediumCreateNote.setOnClickListener {
            binding.highPriorityCreateNote.setBackgroundResource(R.drawable.bg)
            binding.lowCreateNote.setBackgroundResource(R.drawable.bg)
            binding.mediumCreateNote.setBackgroundResource(R.drawable.bg_on_click)
            priorityDefault = "2"
        }

        //save note
        binding.saveFloatingButtonCreateNote.setOnClickListener {

            val title = binding.titleActCreateNote.text.toString()
            val desc = binding.descriptionActCreateNote.text.toString()
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
            Log.e("@@", currentDate)

            val date = (currentDate[0].toString() + currentDate[1].toString())
            val month = (currentDate[3].toString() + currentDate[4].toString())
            val year =
                (currentDate[6].toString() + currentDate[7].toString() + currentDate[8].toString() + currentDate[9].toString())

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
            Log.e(
                "123", " current time : $currentTime, current date: $currentDate, " +
                        "required data format : $requiredDateFormat  "
            )

            if (title.isNotEmpty()) {
                viewModel.insertNote(obj)
                Toast.makeText(requireContext(), "Last updated : $currentTime", Toast.LENGTH_LONG)
                    .show()
            } else {
                Toast.makeText(requireContext(), "Add a title", Toast.LENGTH_LONG).show()
            }
        }

        binding.backBtnCreateNote.setOnClickListener {
            fragmentManager?.beginTransaction()?.apply {
                replace(R.id.fragmentContainer, HomeFragment()).commit()
            }
        }

        //share a note
        binding.shareBtnCreateNote.setOnClickListener {
            if (binding.titleActCreateNote.text.toString().isNotEmpty()) {
                val intent = Intent(Intent.ACTION_SEND)
                intent.type = "text/plain"
                intent.putExtra(
                    Intent.EXTRA_TEXT,
                    "${binding.titleActCreateNote.text}, ${binding.descriptionActCreateNote.text}"
                )

                //chooser where to send and it will display all apps capable of sharing in android device
                val chooser = Intent.createChooser(intent, "Share this using, ")
                startActivity(chooser)
            } else {
                Toast.makeText(requireContext(), "Add a title", Toast.LENGTH_SHORT).show()
            }
        }
        return binding.root
    }

}