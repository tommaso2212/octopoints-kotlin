package com.example.octopoints.ui.homepage.players

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.appcompat.app.AlertDialog
import com.example.octopoints.databinding.UserItemBinding
import com.example.octopoints.model.data.user.User

class PlayersAdapter(private val context: Context, private val inflater: LayoutInflater, private val users: List<User>, private val playersViewModel: PlayersViewModel): BaseAdapter() {

    private lateinit var binding: UserItemBinding

    override fun getCount(): Int {
        return users.size
    }

    override fun getItem(p0: Int): Any {
        return users[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        binding = UserItemBinding.inflate(inflater, p2, false)

        binding.username.text = users[p0].username
        binding.win.text = users[p0].win.toString()
        binding.draw.text = users[p0].draw.toString()
        binding.lose.text = users[p0].lose.toString()

        binding.delete.setOnClickListener {
            AlertDialog.Builder(context)
                .setMessage("Eliminare partita?")
                .setCancelable(false)
                .setPositiveButton("Elimina") { dialog, it ->
                    playersViewModel.deleteUser(users[p0])
                }.setNegativeButton("No") { dialog, it ->
                    dialog.dismiss()
                }.create().show()
        }

        return binding.root
    }
}