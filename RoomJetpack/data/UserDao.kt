package com.example.focus_app.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/*

2 - DAO

data access
we use DAOs to write the methods that we need to handle the data in our tables (entities)

 */

@Dao
interface UserDao {

    // function 1: add users
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)    // suspend - for co-routines later, fun - function

    // function 2: read all data (list all users)
    @Query( "SELECT * FROM User_Table ORDER BY id ASC")
    fun readAllData(): LiveData<List<User>>
}
