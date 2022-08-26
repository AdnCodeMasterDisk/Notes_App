package com.pratyakshkhurana.notes.Adapter

import android.graphics.Color
import android.util.Log.i
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.pratyakshkhurana.notes.Data_or_Model.NotesEntity
import com.pratyakshkhurana.notes.MainActivity
import com.pratyakshkhurana.notes.R
import kotlinx.android.synthetic.main.recycler_view_item_note.view.*

class NotesAdapter(private val listener: MainActivity, private val allNotes: List<NotesEntity>) :
    RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val viewHolder = LayoutInflater.from(listener)
            .inflate(R.layout.recycler_view_item_note, parent, false)
        return NotesViewHolder(viewHolder)
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val curr = allNotes[position]
        holder.title.text = curr.title
        holder.desc.text = curr.description
        holder.date.text = curr.lastUpdatedDate
        when ((1..5).random()) {
            1 -> holder.card.setCardBackgroundColor(Color.parseColor("#ffcc80"))
            2 -> holder.card.setCardBackgroundColor(Color.parseColor("#fbaa93"))
            3 -> holder.card.setCardBackgroundColor(Color.parseColor("#e7ed9b"))
            4 -> holder.card.setCardBackgroundColor(Color.parseColor("#81deea"))
            5 -> holder.card.setCardBackgroundColor(Color.parseColor("#cf94da"))
        }
    }

    override fun getItemCount(): Int {
        return allNotes.size
    }

    class NotesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.title_recyclerView
        val desc: TextView = itemView.desc_recyclerView
        val date: TextView = itemView.date_recyclerView
        val card: CardView = itemView.cardViewRecyclerView
    }


}