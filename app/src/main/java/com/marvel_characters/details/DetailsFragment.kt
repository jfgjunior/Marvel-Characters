package com.marvel_characters.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.marvel_characters.R
import com.marvel_characters.common.utils.networkImage
import com.marvel_characters.common.utils.updateTextOrFallback
import com.marvel_characters.home.data.models.Character
import kotlinx.android.synthetic.main.fragment_details.*
import kotlinx.android.synthetic.main.item_character.characterImage
import kotlinx.android.synthetic.main.item_character.characterName

class DetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            bindView(DetailsFragmentArgs.fromBundle(it).character)
        }
    }

    private fun bindView(character: Character) {
        characterImage.networkImage(character.thumbnail.url, R.drawable.female_placeholder)
        characterName.updateTextOrFallback(
            character.name,
            requireContext().getString(R.string.name_fallback)
        )
        characterDescription.updateTextOrFallback(
            character.description,
            requireContext().getString(R.string.description_fallback)
        )
        comicPriceButton.setOnClickListener {
            val detailsDirections =
                DetailsFragmentDirections.actionDetailsFragmentToComicFragment(character.id)
            findNavController().navigate(detailsDirections)
        }
    }
}