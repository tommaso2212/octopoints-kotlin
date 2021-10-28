package com.example.octopoints.model.dataaccess.team

import android.content.Context
import com.example.octopoints.model.data.team.JoiningTeam
import com.example.octopoints.model.data.team.Team
import com.example.octopoints.model.data.team.TeamWithUsers
import com.example.octopoints.model.dataaccess.DataAccess
import kotlinx.coroutines.runBlocking

class TeamDataAccess(context: Context): DataAccess(context) {
    private val teamDao = db.teamDao()

    fun createTeam(team: Team): Long {
        var id: Long
        runBlocking {
            id = teamDao.insertTeam(team)
        }
        return id
    }

    fun joinTeam(joiningTeam: JoiningTeam) {
        runBlocking {
            teamDao.insertJoiningTeam(joiningTeam)
        }
    }

    fun deleteTeam(team: Team) {
        runBlocking {
            teamDao.deleteTeam(team)
        }
    }

    fun leaveTeam(joiningTeam: JoiningTeam) {
        runBlocking {
            teamDao.deleteJoiningTeam(joiningTeam)
        }
    }

    fun updateTeam(team: Team) {
        runBlocking {
            teamDao.updateTeam(team)
        }
    }

    fun getTeamsByMatchId(id: Long): List<TeamWithUsers>{
        var teams: List<TeamWithUsers>
        runBlocking {
            teams = teamDao.getTeamsByMatchId(id)
        }
        return teams
    }

    fun getTeamWithUsers(id: Long): TeamWithUsers {
        var teamWithUsers: TeamWithUsers
        runBlocking {
            teamWithUsers = teamDao.getTeamWithUsers(id)
        }
        return teamWithUsers
    }
}