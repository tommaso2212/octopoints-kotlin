package com.example.octopoints.model.data.user

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true) val user_id: Long = 0,
    var username: String,
    var win: Int = 0,
    var draw: Int = 0,
    var lose: Int = 0
)