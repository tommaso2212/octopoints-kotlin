package com.example.octopoints.ui.homepage.matches

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.octopoints.model.data.match.Match
import com.example.octopoints.model.dataaccess.match.MatchDataAccess

class MatchesViewModel: ViewModel() {

    val matches: MutableLiveData<List<Match>> by lazy {
        MutableLiveData<List<Match>>()
    }

    private lateinit var matchDataAccess: MatchDataAccess

    fun initDataAccess(context: Context){
        matchDataAccess = MatchDataAccess(context)
        getMatches()
    }

    private fun getMatches(){
        matches.value = matchDataAccess.getMatchesList()
    }

    fun createMatch(description: String): Long{
        return matchDataAccess.createMatch(Match(description = description))
    }

    fun deleteMatch(match: Match){
        matchDataAccess.removeMatch(match)
        getMatches()
    }
}