package com.example.octopoints.ui.teams

import android.content.Context
import com.example.octopoints.model.data.match.MatchWithTeams
import com.example.octopoints.model.data.team.JoiningTeam
import com.example.octopoints.model.data.team.Team
import com.example.octopoints.model.data.team.TeamWithUsers
import com.example.octopoints.model.data.user.User
import com.example.octopoints.ui.common.CommonViewModel

class TeamViewModel(context: Context, private val id: Long) :  CommonViewModel<MatchWithTeams>(context){

    init {
        loadMatchWithUsers()
    }

    private fun loadMatchWithUsers(){
        data.value = matchDataAccess.getMatch(id)
    }

    fun createTeam(): Long{
        val id = teamDataAccess.createTeam(Team(match_id = id))
        loadMatchWithUsers()
        return id
    }

    fun updateTeam(teamWithUsers: TeamWithUsers){
        teamDataAccess.updateTeam(teamWithUsers.team)
        loadMatchWithUsers()
    }

    fun deleteTeam(teamWithUsers: TeamWithUsers){
        teamDataAccess.deleteTeam(teamWithUsers.team)
        loadMatchWithUsers()
    }

    fun getUsers(): List<User>{
        var userList = userDataAccess.getUsersList()

        for(team: TeamWithUsers in data.value!!.teams){
            for (user: User in team.users){
                userList.remove(user)
            }
        }
        return userList
    }

    fun joinTeam(user_id: Long, team_id: Long){
        teamDataAccess.joinTeam(JoiningTeam(team_id, user_id))
    }

    fun leaveTeam(user_id: Long, team_id: Long){
        teamDataAccess.leaveTeam(JoiningTeam(team_id, user_id))
    }
}