package com.pratyakshkhurana.notes.Adapter

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.pratyakshkhurana.notes.Data_or_Model.NotesEntity
import com.pratyakshkhurana.notes.MainActivity
import com.pratyakshkhurana.notes.R
import com.pratyakshkhurana.notes.ViewModel.NotesViewModel
import kotlinx.android.synthetic.main.recycler_view_item_note.view.*

class NotesAdapter(
    private val listener: MainActivity,
    private val allNotes: List<NotesEntity>,
    private val listen: OnClickCardView
) :
    RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val viewHolder = LayoutInflater.from(listener)
            .inflate(R.layout.recycler_view_item_note, parent, false)
        return NotesViewHolder(viewHolder)
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val curr = allNotes[position]
        holder.title.text = curr.title
        holder.description.text = curr.description
        holder.date.text = curr.requiredDateFormat
        holder.card.setCardBackgroundColor(Color.parseColor(curr.noteColourBackground))


        //id will also be passed
        holder.card.setOnClickListener {
            listen.viewClickedOnEdit(curr)
        }

        holder.title.setOnClickListener {
            listen.viewClickedOnEdit(curr)
        }
        holder.description.setOnClickListener {
            listen.viewClickedOnEdit(curr)
        }
        holder.date.setOnClickListener {
            listen.viewClickedOnEdit(curr)
        }
        holder.delete.setOnClickListener {

            AlertDialog.Builder(listener)
                .setTitle("Alert !")
                .setIcon(R.drawable.danger)
                .setMessage("Are you sure you want to delete ?")
                .setCancelable(false)
                .setNegativeButton("NO") { _, _ ->
                }
                .setPositiveButton("YES") { _: DialogInterface, _: Int ->
                    listener.del(curr)
                }.show()
        }
    }

    override fun getItemCount(): Int {
        return allNotes.size
    }

    class NotesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.title_recyclerView
        val description: TextView = itemView.desc_recyclerView
        val date: TextView = itemView.date_recyclerView
        val card: CardView = itemView.cardViewRecyclerView
        val delete: ImageView = itemView.delete_button_recyclerView
    }
}

interface OnClickCardView {
    fun viewClickedOnEdit(note: NotesEntity)
    fun del(note: NotesEntity)
}