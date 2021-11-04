package com.example.octopoints.ui.homepage.players

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.octopoints.R
import com.example.octopoints.databinding.BottomSheetCreateBinding
import com.example.octopoints.databinding.FragmentPlayersBinding
import com.example.octopoints.model.data.user.User
import com.example.octopoints.ui.common.CommonViewModelFactory
import com.google.android.material.bottomsheet.BottomSheetDialog

class PlayersFragment: Fragment() {

    private var _binding: FragmentPlayersBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val playersViewModel = ViewModelProvider(this, CommonViewModelFactory<List<User>>(this.requireContext())).get(PlayerViewModel::class.java)

        playersViewModel.data.observe(viewLifecycleOwner, Observer {
            binding.userList.adapter = PlayerAdapter(this.requireContext(), it, {user -> playersViewModel.deleteUser(user)}, getString(R.string.delete_user))
        })

        _binding = FragmentPlayersBinding.inflate(inflater, container, false)

        binding.createUser.setOnClickListener{
            val bottomModal = BottomSheetDialog(this.requireContext())
            val modalBinding = BottomSheetCreateBinding.inflate(inflater, container, false)

            modalBinding.title.text = getString(R.string.new_user)
            modalBinding.input.hint = getString(R.string.insert_username)
            modalBinding.confirm.setOnClickListener {
                if (modalBinding.input.text.toString() != ""){
                    playersViewModel.createUser(modalBinding.input.text.toString())
                    bottomModal.dismiss()
                } else {
                    Toast.makeText(this.requireContext(), getString(R.string.insert_username), Toast.LENGTH_SHORT).show()
                }
            }
            bottomModal.setContentView(modalBinding.root)
            bottomModal.show()
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}