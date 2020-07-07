package com.example.notes.ViewModel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.notes.Database.Note
import com.example.notes.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NoteViewModel(application: Application): ViewModel(){

    private val repository = Repository(application)
    private val noteLiveData: MutableLiveData<MutableList<Note>> = MutableLiveData()

//    init {
//        CoroutineScope(Main).launch {
//            noteLiveData.value = show()
//        }
//    }

    fun getNotes(): MutableLiveData<MutableList<Note>>{
        return noteLiveData
    }


//    private suspend fun show(): MutableList<Note>{
//        return repository.listOfNotes()
//    }

}