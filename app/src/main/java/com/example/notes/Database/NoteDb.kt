package com.example.notes.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Note::class], version = 1)
abstract class NoteDb: RoomDatabase() {

    abstract fun noteDao(): NoteDao


    companion object {
        private var db: NoteDb? = null


        @Synchronized
        fun getDbInstance(context: Context): NoteDb? {
            if (db == null) {
                db = Room.databaseBuilder(
                    context.applicationContext,
                    NoteDb::class.java,
                    "database_name"
                )
                    .build()
            }
                return db
        }
    }



}