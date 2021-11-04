package com.example.octopoints.ui.homepage.matches

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.octopoints.R
import com.example.octopoints.databinding.BottomSheetCreateBinding
import com.example.octopoints.databinding.FragmentMatchesBinding
import com.example.octopoints.model.data.match.Match
import com.example.octopoints.ui.common.CommonViewModelFactory
import com.example.octopoints.ui.teams.TeamsActivity
import com.google.android.material.bottomsheet.BottomSheetDialog

class MatchesFragment: Fragment() {

    private var _binding: FragmentMatchesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        //val matchesViewModel = ViewModelProvider(this, CommonViewModelFactory<List<Match>>(this.requireContext())).get(MatchViewModel::class.java)
        val matchesViewModel: MatchViewModel by viewModels { CommonViewModelFactory<List<Match>>(this.requireContext()) }
        _binding = FragmentMatchesBinding.inflate(inflater, container, false)

        matchesViewModel.data.observe(viewLifecycleOwner, Observer {
            binding.matchList.adapter = MatchAdapter(requireContext(), it) { match ->
                matchesViewModel.deleteMatch(
                    match
                )
            }
            binding.matchList.setOnItemClickListener { adapterView, _, i, _ ->
                val match = adapterView.getItemAtPosition(i) as Match
                startTeamsActivity(match.match_id.toString())
            }
        })

        binding.createMatch.setOnClickListener {
            val bottomModal = BottomSheetDialog(this.requireContext())
            val modalBinding = BottomSheetCreateBinding.inflate(inflater, container, false)

            modalBinding.title.text = getString(R.string.new_match)
            modalBinding.input.hint = getString(R.string.insert_description)
            modalBinding.confirm.setOnClickListener {
                if (modalBinding.input.text.toString() != ""){
                    startTeamsActivity((matchesViewModel.createMatch(modalBinding.input.text.toString()).toString()))
                    bottomModal.dismiss()
                } else {
                    Toast.makeText(this.requireContext(), getString(R.string.insert_description), Toast.LENGTH_SHORT).show()
                }
            }
            bottomModal.setContentView(modalBinding.root)
            bottomModal.show()
        }

        return binding.root
    }

    private fun startTeamsActivity(match_id: String){
        startActivity(Intent(this.requireContext(), TeamsActivity::class.java).apply {
            putExtra("match_id", match_id)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}