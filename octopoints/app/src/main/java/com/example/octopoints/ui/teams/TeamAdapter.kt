package com.example.octopoints.ui.teams

import android.content.Context
import android.view.LayoutInflater
import com.example.octopoints.R
import com.example.octopoints.databinding.TeamItemBinding
import com.example.octopoints.model.data.team.TeamWithUsers
import com.example.octopoints.ui.common.CommonAdapter

class TeamAdapter(context: Context, list: List<TeamWithUsers>, var onDelete: (team: TeamWithUsers)->Unit) : CommonAdapter(context, list) {
    override fun setView(item: Any) {
        val teamWithUsers = item as TeamWithUsers

        val teamItemBinding = TeamItemBinding.inflate(LayoutInflater.from(context), binding.root, false)

        println(teamWithUsers.toString())

        teamItemBinding.partial.setText(teamWithUsers.team.partial)
        teamItemBinding.total.setText(teamWithUsers.team.total)

        teamItemBinding.delete.setOnClickListener {
            buildDialog(context.getString(R.string.delete_team)) { onDelete(teamWithUsers) }
        }

        teamItemBinding.total.isFocusable = false
        teamItemBinding.partial.isFocusable = false
        teamItemBinding.delete.isFocusable = false
        binding.root.addView(teamItemBinding.root)
    }
}