package com.pratyakshkhurana.notes.Data_or_Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notesTable")
data class NotesEntity(
    @PrimaryKey(autoGenerate = true) val id: Int? = 0,
    val title: String,
    val description: String?,
    val lastUpdatedTime: String,
    val lastUpdatedDate: String,
    val priority: String
)


//annotate it to make it an entity by annotating it with @Entity
//table will be formed in sqlite for this entity
//table name = "notes_table"
//columns will be the things passed in the constructor of entity

//to use this table we need dao
//data access object (dao) needs for performing SQL queries
//and this data will be passed on to the repository
//@ColumnInfo is column name (good practice)

//annotate to make it an entity(table)
//table will have all these columns stored in room DB

//val id :Int =0
//it will auto increment so made primary key out of it, so no need to
//pass in the constructor