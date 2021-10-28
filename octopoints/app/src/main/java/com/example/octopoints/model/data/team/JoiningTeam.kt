package com.example.octopoints.model.data.team

import androidx.room.Entity
import androidx.room.ForeignKey
import com.example.octopoints.model.data.user.User

//Many to many
@Entity(primaryKeys = ["team_id", "user_id"], tableName = "joiningteam", foreignKeys = [
    ForeignKey(
        entity = Team::class,
        parentColumns = ["team_id"],
        childColumns = ["team_id"],
        onDelete = ForeignKey.CASCADE
    ),
    ForeignKey(
        entity = User::class,
        parentColumns = ["user_id"],
        childColumns = ["user_id"],
        onDelete = ForeignKey.CASCADE
    )
])
data class JoiningTeam(
    val team_id: Long,
    val user_id: Long
)