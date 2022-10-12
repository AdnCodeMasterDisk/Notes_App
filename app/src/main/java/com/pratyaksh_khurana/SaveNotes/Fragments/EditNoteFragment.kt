package com.pratyaksh_khurana.SaveNotes.Fragments

import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.pratyaksh_khurana.SaveNotes.Model.NotesEntity
import com.pratyaksh_khurana.SaveNotes.R
import com.pratyaksh_khurana.SaveNotes.ViewModel.NotesViewModel
import com.pratyaksh_khurana.SaveNotes.databinding.FragmentEditNoteBinding
import java.time.LocalTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

class EditNoteFragment : Fragment() {
    private lateinit var binding: FragmentEditNoteBinding
    private var priorityDefault: String = "1" //high
    private val viewModel: NotesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentEditNoteBinding.inflate(layoutInflater, container, false)

        val args = this.arguments

        val title = args?.get("title").toString()
        val description = args?.get("description").toString()
        val priority = args?.get("priority").toString()
        val notesColourBackground = args?.get("notesColourBackground").toString()
        val id = args?.get("id")

        when (priority) {
            "1" -> {
                binding.highPriorityCreateNote.setBackgroundResource(R.drawable.bg_on_click)
                binding.lowCreateNote.setBackgroundResource(R.drawable.bg)
                binding.mediumCreateNote.setBackgroundResource(R.drawable.bg)
            }
            "2" -> {
                binding.highPriorityCreateNote.setBackgroundResource(R.drawable.bg)
                binding.lowCreateNote.setBackgroundResource(R.drawable.bg)
                binding.mediumCreateNote.setBackgroundResource(R.drawable.bg_on_click)
            }
            "3" -> {
                binding.highPriorityCreateNote.setBackgroundResource(R.drawable.bg)
                binding.lowCreateNote.setBackgroundResource(R.drawable.bg_on_click)
                binding.mediumCreateNote.setBackgroundResource(R.drawable.bg)
            }
        }

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


        binding.titleActEditNote.setText(title)
        binding.descriptionActEditNote.setText(description)
        if (binding.descriptionActEditNote.text.isEmpty()) {
            binding.descriptionActEditNote.hint = "Add description..."
            binding.descriptionActEditNote.hintTextColors
        }


        val time: LocalTime = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            LocalTime.now(ZoneId.systemDefault())
        } else {
            TODO("VERSION.SDK_INT < O")
        }

        val dtf: DateTimeFormatter = DateTimeFormatter.ofPattern("hh:mm a", Locale.ENGLISH)
        val currentTime = dtf.format(time) // Alternatively, time.format(dtf)

        binding.saveFloatingButtonEditNote.setOnClickListener {
            val newNote = NotesEntity(
                id = id as Int?,
                title = binding.titleActEditNote.text.toString(),
                description = binding.descriptionActEditNote.text.toString(),
                lastUpdatedTime = currentTime,
                lastUpdatedDate = currentDateFormat(),
                priority = priorityDefault,
                noteColourBackground = notesColourBackground,
                requiredDateFormat = currentDateFormat()
            )

            //take reference of view model and insert
            viewModel.updateNote(newNote)
            Toast.makeText(requireContext(), "Last updated : $currentTime", Toast.LENGTH_SHORT)
                .show()


        }

        binding.backBtnCreateNote.setOnClickListener {
            fragmentManager?.beginTransaction()?.apply {
                replace(R.id.fragmentContainer, HomeFragment())
                    .commit()
            }
        }

        binding.shareBtnCreateNote.setOnClickListener {
            if (binding.titleActEditNote.text.toString().isNotEmpty()) {
                val intent = Intent(Intent.ACTION_SEND)
                intent.type = "text/plain"
                intent.putExtra(
                    Intent.EXTRA_TEXT,
                    "${binding.titleActEditNote.text}, ${binding.descriptionActEditNote.text}"
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

    private fun currentDateFormat(): String {
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