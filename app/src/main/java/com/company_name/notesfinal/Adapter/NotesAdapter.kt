package com.company_name.notesfinal.Adapter

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.company_name.notesfinal.Model.NotesEntity
import com.company_name.notesfinal.R
import com.company_name.notesfinal.databinding.RecyclerViewItemNoteBinding

class NotesAdapter(
    private val requireContext: Context, private val notesList: List<NotesEntity>,
    private val listen: OnClick
) :
    RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    class NotesViewHolder(val binding: RecyclerViewItemNoteBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        return NotesViewHolder(
            RecyclerViewItemNoteBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val curr = notesList[position]

        //bind data in recycler view
        holder.binding.titleRecyclerView.text = curr.title
        holder.binding.descRecyclerView.text = curr.description
        holder.binding.dateRecyclerView.text = curr.requiredDateFormat
        holder.binding.cardViewRecyclerView.setCardBackgroundColor(Color.parseColor(curr.noteColourBackground))
        holder.binding.priority.text = curr.priority

        holder.binding.cardViewRecyclerView.setOnClickListener {
            listen.onClick(curr)
        }
        holder.binding.titleRecyclerView.setOnClickListener {
            listen.onClick(curr)
        }
        holder.binding.descRecyclerView.setOnClickListener {
            listen.onClick(curr)
        }
        holder.binding.dateRecyclerView.setOnClickListener {
            listen.onClick(curr)
        }
        holder.binding.deleteButtonRecyclerView.setOnClickListener {
            //alert the user
            AlertDialog.Builder(requireContext)
                .setTitle("Alert !")
                .setIcon(R.drawable.danger)
                .setMessage("Are you sure you want to delete ?")
                .setCancelable(false)
                .setNegativeButton("NO") { _, _ ->
                }
                .setPositiveButton("YES") { _: DialogInterface, _: Int ->
                    listen.deleteNote(curr)
                }.show()
        }

    }

    override fun getItemCount(): Int {
        return notesList.size
    }

}

//interface for call backs
interface OnClick {
    fun onClick(note: NotesEntity)
    fun deleteNote(note: NotesEntity)
}