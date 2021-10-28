package com.example.octopoints.model.dataaccess.match

import android.content.Context
import com.example.octopoints.model.data.match.Match
import com.example.octopoints.model.data.match.MatchWithTeams
import com.example.octopoints.model.dataaccess.DataAccess
import kotlinx.coroutines.runBlocking

class MatchDataAccess(context: Context): DataAccess(context) {

    private val matchDao = db.matchDao()

    fun createMatch(match: Match): Long {
        var id: Long
        runBlocking {
            id = matchDao.insertMatch(match)
        }
        return id
    }

    fun removeMatch(match: Match) {
        runBlocking {
            matchDao.deleteMatch(match)
        }
    }

    fun getMatchesList(): MutableList<Match> {
        var matches: MutableList<Match>
        runBlocking {
            matches = matchDao.getAll().toMutableList()
        }
        return matches
    }

    fun getMatch(id: Long): MatchWithTeams {
        var match: MatchWithTeams
        runBlocking {
            match = matchDao.getMatchById(id)
        }
        return match
    }
}