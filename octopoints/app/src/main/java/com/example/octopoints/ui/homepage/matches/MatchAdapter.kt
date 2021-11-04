package com.example.octopoints.ui.homepage.matches

import android.content.Context
import android.view.LayoutInflater
import com.example.octopoints.R
import com.example.octopoints.databinding.MatchItemBinding
import com.example.octopoints.model.data.match.Match
import com.example.octopoints.ui.common.CommonAdapter

class MatchAdapter(context: Context, list: List<Match>, var onDelete: (match: Match)->Unit) : CommonAdapter(context, list) {
    override fun setView(item: Any) {
        val match = item as Match

        val matchBinding = MatchItemBinding.inflate(LayoutInflater.from(context), binding.root, false)

        matchBinding.description.text = match.description
        matchBinding.delete.isFocusable = false
        matchBinding.delete.setOnClickListener {
            buildDialog(context.getString(R.string.delete_match)) { onDelete(match) }
        }

        binding.root.addView(matchBinding.root)
    }
}