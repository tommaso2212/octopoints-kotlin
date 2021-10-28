package com.example.octopoints.ui.homepage.matches

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import androidx.appcompat.app.AlertDialog
import com.example.octopoints.databinding.MatchItemBinding
import com.example.octopoints.model.data.match.Match

class MatchesAdapter(private val context: Context, private val layoutInflater: LayoutInflater, private val matches: List<Match>, private val matchesViewModel: MatchesViewModel): BaseAdapter() {
    override fun getCount(): Int {
        return matches.size
    }

    override fun getItem(p0: Int): Any {
        return matches[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val binding = MatchItemBinding.inflate(layoutInflater, p2, false)

        binding.description.text = matches[p0].description

        binding.delete.setOnClickListener {
            AlertDialog.Builder(context)
                .setMessage("Eliminare partita?")
                .setCancelable(false)
                .setPositiveButton("Elimina") { dialog, it ->
                    matchesViewModel.deleteMatch(matches[p0])
                }.setNegativeButton("No") { dialog, it ->
                    dialog.dismiss()
                }.create().show()
        }

        return binding.root
    }

}