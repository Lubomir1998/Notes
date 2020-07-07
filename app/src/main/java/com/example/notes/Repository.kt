package com.example.notes

import android.app.Application
import android.os.AsyncTask
import com.example.notes.Database.Note
import com.example.notes.Database.NoteDao
import com.example.notes.Database.NoteDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class Repository(application: Application) {

    private val noteDb = NoteDb.getDbInstance(application)
    var noteDao: NoteDao = noteDb!!.noteDao()
   // suspend fun listOfNotes() = noteDao.getAll()

    fun insert(note: Note){
        CoroutineScope(IO).launch {
            addNote(noteDao, note)
        }
       // InsertTask(noteDao).execute(note)
    }

    fun update(note: Note){
        CoroutineScope(IO).launch {
            updateNote(noteDao, note)
        }
      //  UpdateTask(noteDao).execute(note)
    }

    fun delete(note: Note){
        CoroutineScope(IO).launch {
            deleteNote(noteDao, note)
        }
       // DeleteTask(noteDao).execute(note)
    }


    private suspend fun addNote(noteDao: NoteDao, note: Note){
        noteDao.insert(note)
    }

//    inner class InsertTask(private val noteDao: NoteDao): AsyncTask<Note, Void, Void>(){
//
//        override fun doInBackground(vararg params: Note): Void? {
//            noteDao.insert(params[0])
//            return null
//        }
//
//
//    }

    private suspend fun updateNote(noteDao: NoteDao, note: Note){
        noteDao.update(note)
    }

//       inner class UpdateTask(private val noteDao: NoteDao) : AsyncTask<Note, Void, Void>() {
//
//            override fun doInBackground(vararg params: Note): Void? {
//                noteDao.update(params[0])
//                return null
//            }
//
//        }


    private suspend fun deleteNote(noteDao: NoteDao, note: Note){
        noteDao.delete(note)
    }

//        inner class DeleteTask(private val noteDao: NoteDao) : AsyncTask<Note, Void, Void>() {
//
//            override fun doInBackground(vararg params: Note): Void? {
//                noteDao.delete(params[0])
//                return null
//            }
//
//        }




}