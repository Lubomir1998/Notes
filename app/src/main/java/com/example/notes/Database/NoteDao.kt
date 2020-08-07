package com.example.notes.Database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDao {

    @Insert
    suspend fun insert(note: Note)

    @Update
    suspend fun update(note: Note)

    @Delete
    suspend fun delete(note: Note)

    @Query("SELECT * FROM Note")
    fun getAll(): LiveData<List<Note>>

    @Query("SELECT * FROM Note WHERE id = :id")
    fun getNoteById(id: Int): Note

}