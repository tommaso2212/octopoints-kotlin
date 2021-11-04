package com.example.octopoints.ui.homepage.players

import android.content.Context
import android.view.LayoutInflater
import androidx.core.view.isVisible
import com.example.octopoints.databinding.UserItemBinding
import com.example.octopoints.model.data.user.User
import com.example.octopoints.ui.common.CommonAdapter

class PlayerAdapter(context: Context, list: List<Any>, var onDelete: (user: User)->Unit, private val deleteDialogTitle: String, private val showDelete: Boolean = true) : CommonAdapter(context, list) {
    override fun setView(item: Any) {
        val user = item as User
        val userBinding = UserItemBinding.inflate(LayoutInflater.from(context), binding.root, false)

        userBinding.username.text = user.username
        userBinding.win.text = user.win.toString()
        userBinding.draw.text = user.draw.toString()
        userBinding.lose.text = user.lose.toString()
        if (showDelete){
            userBinding.delete.isFocusable = false
            userBinding.delete.setOnClickListener {
                buildDialog(deleteDialogTitle) {onDelete(user)}
            }
        } else {
            userBinding.delete.isVisible = false
        }

        binding.root.addView(userBinding.root)
    }
}