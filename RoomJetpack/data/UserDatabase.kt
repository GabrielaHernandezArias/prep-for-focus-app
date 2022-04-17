package com.example.focus_app.data

import android.content.Context  // NOT SURE IF I WAS SUPPOSED TO DO THIS?
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.InternalCoroutinesApi  // APPARENTLY KOTLINX is needed??
import kotlinx.coroutines.internal.synchronized

/*

3 - DATABASE

"Contains the database holder and serves as the main access point for the underlying connection to your app´s persisted (?) relational data"

 */

@Database(entities = [User::class], version = 1, exportSchema = false)

abstract class UserDatabase : RoomDatabase() {

    // FUNCTION 1: user in database ?
    abstract fun userDao(): UserDao

    // instance variable:
    companion object {
        //@Volatile
        var INSTANCE: UserDatabase? = null

        // FUNCTION 2: get whole database ?
        @InternalCoroutinesApi
        fun getDatabase(context: Context): UserDatabase{
            val tempInstance = INSTANCE

            // if instance already exists (is not null) then we are returning that same instance
            // we're always using the same instance for room database
            if (tempInstance != null){
                return tempInstance
            }

            // if there is no instance, we create a new one
            synchronized(this){
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        UserDatabase::class.java,
                        "user_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        } // end of function 2
    }
}
