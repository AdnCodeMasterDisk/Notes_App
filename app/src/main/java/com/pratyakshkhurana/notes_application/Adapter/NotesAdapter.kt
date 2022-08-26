package com.pratyakshkhurana.notes_application.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pratyakshkhurana.notes_application.MainActivity
import com.pratyakshkhurana.notes_application.Model.Notes
import com.pratyakshkhurana.notes_application.R
import com.pratyakshkhurana.notes_application.addNote
import kotlinx.android.synthetic.main.note_view_in_recycler_view.view.*
import kotlinx.coroutines.NonDisposableHandle.parent

class NotesAdapter(notesList1: MainActivity, private val notesList: List<Notes>, private val listener: OnClickView) :
    RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {

        val viewHolder = LayoutInflater.from(parent.context)
            .inflate(R.layout.note_view_in_recycler_view, parent, false)
        return NotesViewHolder(viewHolder)
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val curr = notesList[position]
        holder.notesView.text = curr.notes
        holder.dateView.text = curr.date
        holder.titleView.text = curr.title
        holder.subTitleView.text = curr.subTitle
        when (curr.priority) {
            "1" -> {
                holder.priorityView.setImageResource(R.drawable.background_priority1_default)
            }
            "2" -> {
                holder.priorityView.setImageResource(R.drawable.background_priority2_default)
            }
            "3" -> {
                holder.priorityView.setImageResource(R.drawable.background_priority2_default)
            }
        }
        holder.titleView.setOnClickListener{
            listener.onClick(curr)
        }

    }

    override fun getItemCount(): Int {
        return notesList.size
    }

    class NotesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleView: TextView = itemView.titleView
        val subTitleView: TextView = itemView.subTitleView
        val priorityView: ImageView = itemView.priorityView
        val dateView: TextView = itemView.dateView
        val notesView: TextView = itemView.notesView
    }

}

interface OnClickView{
    fun onClick(note:Notes)
}
