package com.example.octopoints.ui.homepage.players

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.octopoints.model.data.user.User
import com.example.octopoints.model.dataaccess.user.UserDataAccess

class PlayersViewModel: ViewModel() {

    val users: MutableLiveData<List<User>> by lazy {
        MutableLiveData<List<User>>()
    }

    private lateinit var userDataAccess: UserDataAccess

    fun initDataAccess(context: Context){
        userDataAccess = UserDataAccess(context)
        getUsers()
    }

    private fun getUsers() {
        users.value = userDataAccess.getUsersList()
    }

    fun createUser(username: String){
        userDataAccess.createUser(User(username = username))
        getUsers()
    }

    fun deleteUser(user: User){
        userDataAccess.deleteUser(user)
        getUsers()
    }

}