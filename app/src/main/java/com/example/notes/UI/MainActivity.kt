package com.example.notes.UI

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.Adapter.MyAdapter
import com.example.notes.Database.Note
import com.example.notes.Database.NoteDb
import com.example.notes.R
import com.example.notes.Repository
import com.example.notes.ViewModel.NoteViewModel
import com.example.notes.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var listener: MyAdapter.OnItemClickListener
    private lateinit var noteList: List<Note>
    private val model: NoteViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        model.repository = Repository(NoteDb.getDbInstance(applicationContext)!!)
        noteList = mutableListOf()

        listener = object : MyAdapter.OnItemClickListener {
            override fun onItemClicked(note: Note) {

                val intent = Intent(applicationContext, AddNote::class.java)
                intent.putExtra("idKey", note.id)
                intent.putExtra("titleKey", note.title)
                intent.putExtra("descKey", note.description)
                startActivity(intent)

            }

        }

        binding.recyclerView.adapter = MyAdapter(mutableListOf(), listener)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.setHasFixedSize(true)


     // swipe to delete
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                noteList = model.noteLiveData.value!!
                model.deleteNote(MyAdapter(noteList, listener).getNoteAt(viewHolder.adapterPosition))
                Toast.makeText(this@MainActivity, "Note deleted", Toast.LENGTH_SHORT).show()
            }
        }).attachToRecyclerView(binding.recyclerView)


        model.noteLiveData.observe(this, Observer {
            displayData(it)
        })


        binding.add.setOnClickListener {
            startActivity(Intent(this, AddNote::class.java))
        }


    }


    private fun displayData(notes: List<Note>) {
        val adapter = binding.recyclerView.adapter as MyAdapter
        adapter.notesList = notes
        adapter.notifyDataSetChanged()
    }


}