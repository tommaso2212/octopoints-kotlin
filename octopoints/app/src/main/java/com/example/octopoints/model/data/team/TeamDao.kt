package com.example.octopoints.model.data.team

import androidx.room.*

@Dao
interface TeamDao {
    @Insert
    suspend fun insertTeam(team: Team): Long

    @Insert
    suspend fun insertJoiningTeam(joiningTeam: JoiningTeam)

    @Delete
    suspend fun deleteTeam(team: Team)

    @Delete
    suspend fun deleteJoiningTeam(joiningTeam: JoiningTeam)

    @Update
    suspend fun updateTeam(team: Team)

    @Transaction
    //@Query("SELECT * FROM teams, joiningteam, users WHERE :match_id=teams.match_id AND teams.team_id = joiningteam.team_id AND joiningteam.user_id = users.user_id")
    //@Query("SELECT * FROM teams LEFT JOIN joiningteam, users ON teams.team_id=joiningteam.team_id AND joiningteam.user_id=users.user_id WHERE :match_id=teams.match_id")
    @Query("SELECT * FROM teams WHERE match_id=:match_id")
    suspend fun getTeamsByMatchId(match_id: Long): List<TeamWithUsers>

    @Transaction
    @Query("SELECT * FROM teams, joiningteam, users WHERE :team_id=teams.team_id AND :team_id=joiningteam.team_id AND joiningteam.user_id = users.user_id")
    suspend fun getTeamWithUsers(team_id: Long): TeamWithUsers
}