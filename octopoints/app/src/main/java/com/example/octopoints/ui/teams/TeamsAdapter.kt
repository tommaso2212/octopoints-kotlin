package com.example.octopoints.ui.teams

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.BaseAdapter
import androidx.appcompat.app.AlertDialog
import com.example.octopoints.databinding.TeamItemBinding
import com.example.octopoints.model.data.team.TeamWithUsers

class TeamsAdapter(private var context: Context, private var layoutInflater: LayoutInflater, private var teams: List<TeamWithUsers>, private var teamsViewModel: TeamsViewModel): BaseAdapter() {
    override fun getCount(): Int {
        return teams.size
    }

    override fun getItem(p0: Int): Any {
        return teams[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val binding = TeamItemBinding.inflate(layoutInflater, p2, false)

        binding.partial.setText(teams[p0].team.partial)
        binding.total.setText(teams[p0].team.total)

        binding.partial.setOnEditorActionListener { textView, i, keyEvent ->
            if(i == EditorInfo.IME_ACTION_DONE){
                teamsViewModel.updateTeam(teams[p0].team)
            }
            true
        }
        binding.total.setOnEditorActionListener { textView, i, keyEvent ->
            if(i == EditorInfo.IME_ACTION_DONE){
                teamsViewModel.updateTeam(teams[p0].team)
            }
            true
        }

        binding.delete.setOnClickListener {
            AlertDialog.Builder(context)
                .setMessage("Eliminare squadra?")
                .setCancelable(false)
                .setPositiveButton("Elimina") { dialog, it ->
                    teamsViewModel.deleteTeam(teams[p0])
                }.setNegativeButton("No") { dialog, it ->
                    dialog.dismiss()
                }.create().show()
        }

        return binding.root
    }
}