package com.example.octopoints.ui.teams

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.octopoints.databinding.ActivityTeamsBinding

class TeamsActivity : AppCompatActivity() {

    private var _binding: ActivityTeamsBinding? = null
    private val binding get() = _binding!!
    private lateinit var teamsViewModel: TeamsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        teamsViewModel = ViewModelProvider(this).get(TeamsViewModel::class.java)

        teamsViewModel.initDataAccess(this, intent.getStringExtra("match_id").toString())

        _binding = ActivityTeamsBinding.inflate(layoutInflater)

        teamsViewModel.matchWithTeams.observe(this, Observer {
            setTitle(it.match.description)
        })

        setContentView(binding.root)
    }
}