package com.example.octopoints.model.dataaccess

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.octopoints.model.data.match.Match
import com.example.octopoints.model.data.match.MatchDao
import com.example.octopoints.model.data.team.JoiningTeam
import com.example.octopoints.model.data.team.Team
import com.example.octopoints.model.data.team.TeamDao
import com.example.octopoints.model.data.user.User
import com.example.octopoints.model.data.user.UserDao

@Database(entities = [User::class, Match::class, Team::class, JoiningTeam::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun matchDao(): MatchDao
    abstract fun userDao(): UserDao
    abstract fun teamDao(): TeamDao
}