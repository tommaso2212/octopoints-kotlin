package com.example.octopoints.ui.teams

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.octopoints.model.data.match.MatchWithTeams
import com.example.octopoints.model.data.team.Team
import com.example.octopoints.model.data.team.TeamWithUsers
import com.example.octopoints.model.dataaccess.match.MatchDataAccess
import com.example.octopoints.model.dataaccess.team.TeamDataAccess

class TeamsViewModel: ViewModel() {

    val matchWithTeams: MutableLiveData<MatchWithTeams> by lazy {
        MutableLiveData<MatchWithTeams>()
    }

    private lateinit var teamDataAccess: TeamDataAccess
    private lateinit var matchDataAccess: MatchDataAccess
    private var match_id: Long = 0

    fun initDataAccess(context: Context, id: String){
        teamDataAccess = TeamDataAccess(context)
        matchDataAccess = MatchDataAccess(context)
        match_id = id.toLong()
        getMatchWithTeams()
    }

    private fun getMatchWithTeams(){
        matchWithTeams.value = matchDataAccess.getMatch(match_id)
    }

    fun getTeams(){
        matchWithTeams.value?.teams = teamDataAccess.getTeamsByMatchId(match_id)
    }

    fun updateTeam(team: Team){
        teamDataAccess.updateTeam(team)
        getTeams()
    }

    fun createTeam(): Long{
        val team_id = teamDataAccess.createTeam(Team(match_id = match_id))
        getTeams()
        return team_id
    }

    fun deleteTeam(teamWithUsers: TeamWithUsers){
        teamDataAccess.deleteTeam(teamWithUsers.team)
    }
}