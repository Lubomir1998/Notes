package com.example.notes.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.Database.Note
import com.example.notes.R

class MyAdapter(var notesList: List<Note>, private var itemClicklistener: OnItemClickListener): RecyclerView.Adapter<MyAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int = notesList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val current = notesList[position]

        holder.titleView.text = current.title

        holder.open(current, itemClicklistener)

    }

    fun getNoteAt(position: Int): Note{
        return notesList[position]
    }

    fun setNote(list: List<Note>){
        notesList = list
    }

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val titleView: TextView = itemView.findViewById(R.id.titleTextView)

        fun open(note: Note, listener: OnItemClickListener){
            itemView.setOnClickListener{
                listener.onItemClicked(note)
            }
        }

    }

    interface OnItemClickListener{
        fun onItemClicked(note: Note)
    }

}