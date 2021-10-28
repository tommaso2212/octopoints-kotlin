package com.example.octopoints.model.dataaccess.user

import android.content.Context
import com.example.octopoints.model.data.user.User
import com.example.octopoints.model.dataaccess.DataAccess
import kotlinx.coroutines.runBlocking

class UserDataAccess(context: Context): DataAccess(context) {
    private val userDao = db.userDao()

    fun createUser(user: User): Long{
        var id: Long
        runBlocking {
            id = userDao.insertUser(user)
        }
        return id
    }

    fun deleteUser(user: User) {
        runBlocking {
            userDao.deleteUser(user)
        }
    }

    fun updateUser(user: User) {
        runBlocking {
            userDao.updateUser(user)
        }
    }

    fun updateUsers(users: List<User>) {
        runBlocking {
            userDao.updateUsers(users)
        }
    }

    fun getUsersList(): MutableList<User> {
        var users: MutableList<User>
        runBlocking {
            users = userDao.getAll().toMutableList()
        }
        return users
    }
}