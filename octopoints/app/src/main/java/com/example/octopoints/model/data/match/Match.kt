package com.example.octopoints.model.data.match

import androidx.room.*
import com.example.octopoints.model.data.team.Team
import com.example.octopoints.model.data.team.TeamWithUsers

@Entity(tableName = "matches")
data class Match(
    @PrimaryKey(autoGenerate = true) val match_id: Long = 0,
    val description: String
)