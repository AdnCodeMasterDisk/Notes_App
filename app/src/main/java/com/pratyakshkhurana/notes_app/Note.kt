package com.pratyakshkhurana.notes_app

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


//annotate it to make it an entity by annotating it with @Entity
//table will be formed in sqlite for this entity
//table name = "notes_table"
//columns will be the things passed in the constructor of entity

//to use this table we need dao
//data access object (dao) needs for performing SQL queries
//and this data will be passed on to the repository
//@ColumnInfo is column name (good practice)

@Entity(tableName = "notes_table")
class Note(@ColumnInfo(name = "text") val text: String) {
    //it will auto increment so made primary key out of it, so no need to
    //pass in the constructor
    @PrimaryKey(autoGenerate = true) val id = 0

}
//make DAO to access this table
