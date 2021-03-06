package com.example.octopoints.model.data.match

import androidx.room.*

@Dao
interface MatchDao {
    @Insert
    suspend fun insertMatch(match: Match): Long

    @Delete
    suspend fun deleteMatch(match: Match)

    @Query("SELECT * FROM matches")
    suspend fun getAll(): List<Match>

    @Transaction
    //@Query("SELECT * FROM matches M, users U, joiningteam J, teams T WHERE :match_id=M.match_id AND T.match_id=M.match_id AND T.team_id=J.team_id AND J.user_id=U.user_id")
    @Query("SELECT * FROM matches LEFT JOIN teams ON matches.match_id=teams.match_id WHERE :match_id=matches.match_id")
    suspend fun getMatchById(match_id: Long): MatchWithTeams
}