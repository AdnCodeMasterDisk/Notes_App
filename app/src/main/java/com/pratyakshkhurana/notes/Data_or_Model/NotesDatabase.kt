package com.pratyakshkhurana.notes.Data_or_Model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


//made abstract class, so we can make object of their children only
//will make a singleton of this
// Annotates class to be a Room Database with a table (entity) of the Word class
@Database(
    //entities are all tables, we need to specify in database
    entities = [NotesEntity::class],
    version = 1,
    exportSchema = false
)
//abstract
abstract class NotesDatabase : RoomDatabase() {

    //to access DAO in view model and repository
    abstract val notesDAO: NotesDAO

    //making singleton
    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: NotesDatabase? = null

        fun getDatabase(context: Context): NotesDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            //synchronized(this) no other thread access it while it is running
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NotesDatabase::class.java,
                    "notes_database"
                    //added allowMainThreadQueries()
                ).allowMainThreadQueries().build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}

