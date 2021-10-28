package com.example.octopoints.model.data.user

import androidx.room.*

@Dao
interface UserDao {
    @Insert
    suspend fun insertUser(user: User): Long

    @Delete
    suspend fun deleteUser(user: User)

    @Update
    suspend fun updateUser(user: User)

    @Update
    suspend fun updateUsers(users: List<User>)

    @Query("SELECT * FROM users")
    suspend fun getAll(): List<User>
}