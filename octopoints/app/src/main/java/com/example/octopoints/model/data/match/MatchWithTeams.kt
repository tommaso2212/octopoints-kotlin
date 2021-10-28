package com.example.octopoints.model.data.match

import androidx.room.Embedded
import androidx.room.Relation
import com.example.octopoints.model.data.team.Team
import com.example.octopoints.model.data.team.TeamWithUsers

//Nested relationship - each match has n teams
data class MatchWithTeams(
    @Embedded var match: Match,
    @Relation(
        entity = Team::class,
        parentColumn = "match_id",
        entityColumn = "match_id"
    ) var teams: List<TeamWithUsers>
)