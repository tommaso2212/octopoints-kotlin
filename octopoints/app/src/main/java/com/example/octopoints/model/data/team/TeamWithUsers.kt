package com.example.octopoints.model.data.team

import androidx.room.*
import com.example.octopoints.model.data.user.User

//One to many - each team can have n players
data class TeamWithUsers(
    @Embedded var team: Team,
    @Relation(
        parentColumn = "team_id",
        entityColumn = "user_id",
        associateBy = Junction(JoiningTeam::class)
    ) var users: List<User>
)