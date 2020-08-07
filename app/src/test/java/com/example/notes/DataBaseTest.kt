package com.example.notes

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.notes.Database.Note
import com.example.notes.Database.NoteDb
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class MainActivityTest {

    private lateinit var db: NoteDb

    @Rule @JvmField
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun createDB(){
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            NoteDb::class.java)
            .allowMainThreadQueries()
            .build()
    }

    @After
    fun closeDB(){
        db.close()
    }


    @Test
    fun testInsertUpdateDelete() = runBlocking {
        val note1 = Note("title", "d")

       // db.noteDao().insert(note1)

        val value = db.noteDao().getNoteById(note1.id)

//        assertEquals(value.id, note1.id)

        assertEquals(db.noteDao().getAll().toString(), "asaf")




        }


}