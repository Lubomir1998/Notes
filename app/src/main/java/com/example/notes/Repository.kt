package com.example.notes

import android.app.Application
import android.os.AsyncTask
import com.example.notes.Database.Note
import com.example.notes.Database.NoteDao
import com.example.notes.Database.NoteDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class Repository(noteDb: NoteDb) {

    private var noteDao: NoteDao = noteDb.noteDao()

    suspend fun listOfNotes() = noteDao.getAll()

    suspend fun insert(note: Note){
        noteDao.insert(note)
    }

    suspend fun update(note: Note) {
        noteDao.update(note)
    }

    suspend fun delete(note: Note){
        noteDao.delete(note)
    }



}