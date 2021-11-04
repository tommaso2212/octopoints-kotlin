package com.example.octopoints.ui.teams

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.octopoints.databinding.ActivityTeamsBinding
import com.example.octopoints.model.data.match.MatchWithTeams
import com.example.octopoints.ui.common.CommonViewModelFactory

class TeamsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val binding = ActivityTeamsBinding.inflate(layoutInflater)

        val teamViewModel = ViewModelProvider(this, CommonViewModelFactory<MatchWithTeams>(this, intent.getStringExtra("match_id").toString().toLong())).get(TeamViewModel::class.java)

        teamViewModel.data.observe(this, Observer {
            title = it.match.description
            binding.teamList.adapter = TeamAdapter(this, it.teams) { team ->
                teamViewModel.deleteTeam(team)
            }
        })

        binding.createTeam.setOnClickListener{
            teamViewModel.createTeam()
        }

        setContentView(binding.root)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}