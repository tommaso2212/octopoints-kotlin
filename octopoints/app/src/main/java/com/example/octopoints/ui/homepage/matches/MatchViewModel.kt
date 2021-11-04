package com.example.octopoints.ui.homepage.matches

import android.content.Context
import com.example.octopoints.model.data.match.Match
import com.example.octopoints.ui.common.CommonViewModel

class MatchViewModel(context: Context): CommonViewModel<List<Match>>(context) {

    init {
        loadMatches()
    }

    private fun loadMatches(){
        data.value = matchDataAccess.getMatchesList()
    }

    fun createMatch(description: String): Long{
        val id: Long = matchDataAccess.createMatch(Match(description = description))
        loadMatches()
        return id
    }

    fun deleteMatch(match: Match){
        matchDataAccess.removeMatch(match)
        loadMatches()
    }

}