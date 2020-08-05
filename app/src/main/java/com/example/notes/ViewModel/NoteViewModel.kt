package com.example.notes.ViewModel

import androidx.lifecycle.*
import com.example.notes.Database.Note
import com.example.notes.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NoteViewModel: ViewModel(){

    lateinit var repository: Repository
    val noteLiveData: LiveData<List<Note>> by lazy { repository.listOfNotes() }


    fun addNote(note: Note){
        viewModelScope.launch(Dispatchers.IO) {
                repository.insert(note)
        }
    }

    fun updateNote(note: Note){
        viewModelScope.launch(Dispatchers.IO) {
                repository.update(note)
        }
    }

    fun deleteNote(note: Note){
        viewModelScope.launch(Dispatchers.IO) {
                repository.delete(note)
        }
    }


}