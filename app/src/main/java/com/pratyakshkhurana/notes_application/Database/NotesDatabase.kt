package com.pratyakshkhurana.notes_application.Database

import android.content.Context
import android.provider.ContactsContract
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.pratyakshkhurana.notes_application.Dao.NotesDao
import com.pratyakshkhurana.notes_application.Model.Notes


//made abstract class, so we can make object of their children only
//will make a singleton of this
// Annotates class to be a Room Database with a table (entity) of the Word class
@Database(
    entities = [Notes::class],
    version = 1,
    exportSchema = false
)
abstract class NotesDatabase : RoomDatabase() {

    //to access DAO in view model
    abstract fun getNotesDao(): NotesDao

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


