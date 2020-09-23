package com.marvel_characters.home.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.marvel_characters.R
import com.marvel_characters.home.data.models.Character
import com.marvel_characters.home.di.injector
import com.marvel_characters.home.di.viewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModel {
        injector
            .homeComponentFactory
            .create(::onErrorCallback, ::showPage)
            .homeViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        charactersRecyclerView.adapter = CharactersAdapter(::goToDetails)

        viewModel.characterList.observe(requireActivity(), Observer {
            (charactersRecyclerView.adapter as CharactersAdapter).apply {
                submitList(it)
                if (itemCount != 0) {
                    showPage()
                }
            }
        })
    }

    private fun goToDetails(character: Character) {
        val actionDetail = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(character)
        findNavController().navigate(actionDetail)
    }

    private fun showPage() {
        loadingIndicator.visibility = View.GONE
        charactersRecyclerView.visibility = View.VISIBLE
    }

    private fun onErrorCallback() {
        if (charactersRecyclerView.adapter?.itemCount != 0) {
            findNavController().navigate(R.id.action_homeFragment_to_errorFragment)
        } else {
            Snackbar.make(
                charactersRecyclerView,
                R.string.error_loading_characters,
                Snackbar.LENGTH_SHORT
            ).show()
        }
    }
}