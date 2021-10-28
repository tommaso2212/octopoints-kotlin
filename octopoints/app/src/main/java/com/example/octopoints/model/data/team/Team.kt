package com.example.octopoints.model.data.team

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey
import com.example.octopoints.model.data.match.Match

@Entity(tableName = "teams", foreignKeys = [ForeignKey(entity = Match::class, parentColumns = ["match_id"], childColumns = ["match_id"], onDelete = CASCADE)])
data class Team (
    @PrimaryKey(autoGenerate = true) val team_id: Long = 0,
    val match_id: Long,     //FK match
    val total: Int = 0,     //Punteggio totale
    val partial: Int = 0    //Punteggio parziale
)