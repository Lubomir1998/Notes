package com.example.notes.UI

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.notes.Database.Note
import com.example.notes.Database.NoteDb
import com.example.notes.R
import com.example.notes.Repository
import com.example.notes.ViewModel.NoteViewModel
import com.example.notes.databinding.ActivityAddNoteBinding

class AddNote : AppCompatActivity() {

    private lateinit var binding: ActivityAddNoteBinding

    private val model: NoteViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)

        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Save note"

        model.repository = Repository(NoteDb.getDbInstance(applicationContext)!!)


     // if we are updating the note the title and description fields are set with the text

        if(intent.getIntExtra("idKey", -1) != -1){
            binding.addTitle.setText(intent.getStringExtra("titleKey"))
            binding.addDesc.setText(intent.getStringExtra("descKey"))
        }


        binding.addNewnote.setOnClickListener {
            addOrEditNote()
        }


    }

    private fun addOrEditNote(){
        val noteId = intent.getIntExtra("idKey", -1)

        val title = binding.addTitle.text.toString().trim()
        val description = binding.addDesc.text.toString().trim()

     // update note
        if(noteId != -1) {

            if(title.isEmpty()){
                Toast.makeText(this, "Please, enter a title!", Toast.LENGTH_SHORT).show()
            }
            else { // update the note
                val no = Note(title, description)
                no.id = noteId
                model.updateNote(no)
                Toast.makeText(this, "Note edited", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainActivity::class.java))
            }
        }

     // add note
        else{

            if (title.isEmpty()) {
                Toast.makeText(this, "Please, enter a title!", Toast.LENGTH_SHORT).show()
            }
            else { // add the note
                val n = Note(title, description)
                model.addNote(n)
                Toast.makeText(this, "Note added", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainActivity::class.java))
            }
        }

    }
}