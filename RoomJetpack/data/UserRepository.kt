package com.example.focus_app.data
import androidx.lifecycle.LiveData

/*
REPOSITORY: "CRUD" operations

(create, read, update, delete)

What is a repository? WHAT IS THIS? its NOT an entity, dao or database, so what is it????

a class that abstracts access to multiple data sources ..?
repository would mean storing the metadata of our database?
 */

// parameter: our data access object
class UserRepository(private val userDao: UserDao) {

    // 1. variable:
    // list of users wrapped inside live data object
    // reads data from this user (data access object)
    val readAllData: LiveData<List<User>> = userDao.readAllData()

    // 2. function
    // we use this function to access our addUser
    // suspend used for co-routines
    suspend fun addUser(user: User){
        userDao.addUser(user)
    }

}
