package com.example.octopoints.ui.homepage.players

import android.content.Context
import com.example.octopoints.model.data.user.User
import com.example.octopoints.ui.common.CommonViewModel

class PlayerViewModel(context: Context): CommonViewModel<List<User>>(context) {

    init {
        loadUsers()
    }

    private fun loadUsers(){
        data.value = userDataAccess.getUsersList()
    }

    fun createUser(username: String){
        userDataAccess.createUser(User(username = username))
        loadUsers()
    }

    fun deleteUser(user: User){
        userDataAccess.deleteUser(user)
        loadUsers()
    }
}