package com.example.octopoints.ui.homepage.players

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.octopoints.databinding.BottomSheetCreateBinding
import com.example.octopoints.databinding.FragmentPlayersBinding
import com.google.android.material.bottomsheet.BottomSheetDialog

class PlayersFragment: Fragment() {

    private lateinit var playersViewModel: PlayersViewModel

    private var _binding: FragmentPlayersBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        playersViewModel = ViewModelProvider(this).get(PlayersViewModel::class.java)
        playersViewModel.initDataAccess(this.requireContext())

        playersViewModel.users.observe(viewLifecycleOwner, Observer {
            binding.userList.adapter = PlayersAdapter(inflater, it, playersViewModel)
        })

        _binding = FragmentPlayersBinding.inflate(inflater, container, false)

        binding.createUser.setOnClickListener{
            val bottomModal = BottomSheetDialog(this.requireContext())
            val modalBinding = BottomSheetCreateBinding.inflate(inflater, container, false)

            modalBinding.title.text = "Crea nuovo giocatore"
            modalBinding.input.hint = "Username"
            modalBinding.confirm.setOnClickListener {
                if (modalBinding.input.text.toString() != ""){
                    playersViewModel.createUser(modalBinding.input.text.toString())
                    bottomModal.dismiss()
                } else {
                    Toast.makeText(this.requireContext(), "Inserisci username", Toast.LENGTH_SHORT).show()
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