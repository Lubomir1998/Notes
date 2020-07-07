package com.example.notes.UI

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.Adapter.MyAdapter
import com.example.notes.Database.Note
import com.example.notes.R
import com.example.notes.Repository
import com.example.notes.ViewModel.NoteViewModel
import com.example.notes.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity(),
    MyAdapter.OnItemClickListener {



    private lateinit var binding: ActivityMainBinding

    lateinit var repository: Repository

    private lateinit var noteList: MutableList<Note>
    private lateinit var adapter: MyAdapter
    private lateinit var model: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        repository = Repository(application)
        noteList = mutableListOf()


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
                repository.delete(adapter.getNoteAt(viewHolder.adapterPosition))
                Toast.makeText(this@MainActivity, "Note deleted", Toast.LENGTH_SHORT).show()
            }
        }).attachToRecyclerView(binding.recyclerView)


        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MyAdapter(noteList, this@MainActivity)
        binding.recyclerView.adapter = adapter



        binding.add.setOnClickListener {
            startActivity(Intent(this, AddNote::class.java))
        }

      // show().execute()

        CoroutineScope(Main).launch {
            showAll()
        }


    }

//    inner class show: AsyncTask<Void, Void, Void>(){
//
//        override fun doInBackground(vararg params: Void?): Void? {
//
//            noteList = repository.noteDao.getAll()
//            adapter = MyAdapter(noteList, this@MainActivity)
//            binding.recyclerView.adapter = adapter
//            adapter.notifyDataSetChanged()
//
//            return null
//        }
//    }

    private suspend fun showAll(){
        noteList = repository.noteDao.getAll()
        adapter = MyAdapter(noteList, this@MainActivity)
        binding.recyclerView.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    override fun onItemClicked(note: Note) {
        val intent = Intent(this, AddNote::class.java)
        intent.putExtra("idKey", note.id)
        intent.putExtra("titleKey", note.title)
        intent.putExtra("descKey", note.description)
        startActivity(intent)
    }


}