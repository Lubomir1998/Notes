package com.example.notes.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notes.Database.Note
import com.example.notes.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NoteViewModel: ViewModel(){

    lateinit var repository: Repository
    val noteLiveData: MutableLiveData<List<Note>> = MutableLiveData()


    fun getAllNotesFromDB(){
        viewModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                noteLiveData.value = repository.listOfNotes()
            }
        }

    }



    fun addNote(note: Note){
        viewModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                repository.insert(note)
                getAllNotesFromDB()
            }
        }
    }

    fun updateNote(note: Note){
        viewModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                repository.update(note)
                getAllNotesFromDB()
            }
        }
    }

    fun deleteNote(note: Note){
        viewModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                repository.delete(note)
                getAllNotesFromDB()
            }
        }
    }


}