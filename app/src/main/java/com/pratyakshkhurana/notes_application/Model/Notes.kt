package com.pratyakshkhurana.notes_application.Model

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

//annotate to make it an entity(table)
//table will have all these columns stored in room DB

@Entity(tableName = "notes_table")
class Notes(
    @PrimaryKey(autoGenerate = true) var id: Int? = 0,
    var title: String,
    var subTitle: String,
    var notes: String,
    var date:String,
    var priority: String
) {
    //it will auto increment so made primary key out of it, so no need to
    //pass in the constructor
}

//make DAO to access this table (entity) in Room (wrapper above SQLite)
