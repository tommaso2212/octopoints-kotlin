package com.example.octopoints.ui.homepage.matches

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.octopoints.databinding.BottomSheetCreateBinding
import com.example.octopoints.databinding.FragmentMatchesBinding
import com.example.octopoints.model.data.match.Match
import com.example.octopoints.ui.teams.TeamsActivity
import com.google.android.material.bottomsheet.BottomSheetDialog

class MatchesFragment: Fragment() {

    private lateinit var matchesViewModel: MatchesViewModel

    private var _binding: FragmentMatchesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        matchesViewModel = ViewModelProvider(this).get(MatchesViewModel::class.java)
        matchesViewModel.initDataAccess(this.requireContext())

        _binding = FragmentMatchesBinding.inflate(inflater, container, false)

        matchesViewModel.matches.observe(viewLifecycleOwner, Observer {
            binding.matchList.adapter = MatchesAdapter(this.requireContext(), layoutInflater, it, matchesViewModel)
        })


        binding.matchList.setOnItemClickListener { adapterView, view, i, l ->
            val match = adapterView.getItemAtPosition(i) as Match
            startTeamsActivity(match.match_id.toString())
        }

        binding.createMatch.setOnClickListener {
            val bottomModal = BottomSheetDialog(this.requireContext())
            val modalBinding = BottomSheetCreateBinding.inflate(inflater, container, false)

            modalBinding.title.text = "Crea nuova partita"
            modalBinding.input.hint = "Descrizione"
            modalBinding.confirm.setOnClickListener {
                if (modalBinding.input.text.toString() != ""){
                    matchesViewModel.createMatch(modalBinding.input.text.toString())
                    bottomModal.dismiss()
                } else {
                    Toast.makeText(this.requireContext(), "Inserisci descrizione", Toast.LENGTH_SHORT).show()
                }
            }
            bottomModal.setContentView(modalBinding.root)
            bottomModal.show()
        }

        return binding.root
    }

    fun startTeamsActivity(match_id: String){
        startActivity(Intent(this.requireContext(), TeamsActivity::class.java).apply {
            putExtra("match_id", match_id)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}