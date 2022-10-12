package com.pratyaksh_khurana.SaveNotes.Model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    //entities are all tables, we need to specify in database
    entities = [NotesEntity::class],
    version = 1,
    exportSchema = false
)
//abstract
abstract class NotesDatabase : RoomDatabase() {

    //to access DAO in view model and repository
    abstract val notesDAO: NotesDao

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
                ).allowMainThreadQueries().fallbackToDestructiveMigration().build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}

