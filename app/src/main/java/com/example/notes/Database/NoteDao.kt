package com.example.notes.Database

import androidx.room.*
import com.example.notes.Database.Note

@Dao
interface NoteDao {

    @Insert
    suspend fun insert(note: Note)

    @Update
    suspend fun update(note: Note)

    @Delete
    suspend fun delete(note: Note)

    @Query("SELECT * FROM Note")
    suspend fun getAll(): List<Note>


}