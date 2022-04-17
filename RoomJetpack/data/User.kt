package com.example.focus_app.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date

/*

1 - ENTITY

table USERS
 we use this table to store the info of users, much like SQL we declare column types and names for the table

 */

@Entity(tableName = "User_Table")
data class User(
        @PrimaryKey(autoGenerate = true)   // this automatically generates the primary key numbers
        val id: Int,
        val FirstName: String,
        val LastName: String,
        val Email: String,
        val Birthday: Date
)
