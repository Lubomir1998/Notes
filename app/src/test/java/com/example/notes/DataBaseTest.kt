package com.example.notes

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.notes.Database.Note
import com.example.notes.Database.NoteDb
import com.example.notes.ViewModel.NoteViewModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule @JvmField
    var instantExecutorRule = InstantTaskExecutorRule()

    @Test
    fun testInsertUpdateDelete() {
            // initialize viewModel
            val model = NoteViewModel()
            model.repository = Repository(NoteDb.getDbInstance(ApplicationProvider.getApplicationContext())!!)

            var list = emptyList<Note>()

            val observer = Observer<List<Note>> {
                list = it
            }

            try
            {
                model.noteLiveData.observeForever(observer)

                model.getAllNotesFromDB()

                // add data
                model.addNote(Note("t1", "d1"))

                // check
                assertEquals(list.size, 1)

            } finally {
                model.noteLiveData.removeObserver(observer)
            }
        }


}