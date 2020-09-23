package com.marvel_characters.comic.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.marvel_characters.R
import com.marvel_characters.comic.data.models.Comic
import com.marvel_characters.common.utils.networkImage
import com.marvel_characters.common.utils.updateTextOrFallback
import com.marvel_characters.home.di.injector
import com.marvel_characters.home.di.viewModel
import kotlinx.android.synthetic.main.fragment_comic.*
import kotlinx.android.synthetic.main.layout_comic.*

class ComicFragment : Fragment() {

    private val viewModel: ComicViewModel by viewModel {
        injector
            .comicComponentFactory
            .create(ComicFragmentArgs.fromBundle(requireArguments()).characterId)
            .comicViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_comic, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.comic.observe(requireActivity(),
            Observer {
                bindComic(it)
                loadingState.visibility = View.GONE
                loadedState.visibility = View.VISIBLE
            })

        viewModel.error.observe(requireActivity(),
            Observer {
                goToErrorPage()
            })
    }

    override fun onDestroy() {
        viewModel.onDestroy()
        super.onDestroy()
    }

    private fun bindComic(comic: Comic) {
        comicImage.networkImage(comic.thumbnail.url, R.drawable.female_placeholder)
        comicName.updateTextOrFallback(comic.title, resources.getString(R.string.name_fallback))
        comicDescription.updateTextOrFallback(
            comic.description,
            resources.getString(R.string.description_fallback)
        )
        val price = String.format(
            resources.getString(R.string.comic_price),
            comic.highestPriceOrEmpty.price
        )
        comicPrice.updateTextOrFallback(price, resources.getString(R.string.price_fallback))
    }

    private fun goToErrorPage() {
        findNavController().navigate(R.id.action_comicFragment_to_errorFragment)
    }
}