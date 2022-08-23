package com.pratyakshkhurana.notes_app

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


//annotate it to make it an entity
//table will be formed in sqlite for this entity
//table name = "notes_table"

//to use this table we need dao
//data access object (dao) needs for performing SQL queries
@Entity(tableName = "notes_table")
class Note(@ColumnInfo(name = "text") val text: String) {
    //it will auto increment
    @PrimaryKey(autoGenerate = true) val id = 0

}