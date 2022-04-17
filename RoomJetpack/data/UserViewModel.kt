package com.example.focus_app.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*

/*
VIEW MODEL

view model is a communication center between the repository and the UI
view model's role is to provide data to the UI and survive configuration changes

i THINK here we add all variables and functions we've built to have them all in one place
 */

@InternalCoroutinesApi  // had to add this bc .getDatabase would not work if not

class UserViewModel (application: Application): AndroidViewModel(application) {

    private val readAllData: LiveData<List<User>>
    private val repository: UserRepository

    init {
        val userDao = UserDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDao)
        readAllData = repository.readAllData
    }

    // dispatchers IO means i want this code to run in a worker / background thread
    fun addUser(user: User){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(user)
        }
    }
}
