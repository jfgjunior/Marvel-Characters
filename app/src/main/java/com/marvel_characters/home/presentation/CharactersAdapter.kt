package com.marvel_characters.home.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.marvel_characters.R
import com.marvel_characters.common.utils.networkImage
import com.marvel_characters.common.utils.updateTextOrFallback
import com.marvel_characters.home.data.models.Character
import kotlinx.android.synthetic.main.item_character.view.*

class CharactersAdapter(
    private val clickCallback: (Character) -> Unit
) : PagedListAdapter<Character, CharactersAdapter.CharacterViewHolder>(characterDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_character, parent, false)
        return CharacterViewHolder(view)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        getItem(position)?.let { character ->
            holder.bind(character)
            holder.itemView.setOnClickListener { clickCallback(character) }
        }
    }

    class CharacterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(character: Character) {
            itemView.characterImage.networkImage(
                character.thumbnail.url,
                R.drawable.female_placeholder
            )
            itemView.characterName.updateTextOrFallback(
                character.name,
                itemView.context.getString(R.string.name_fallback)
            )
        }
    }

    companion object {
        private val characterDiffCallback = object : DiffUtil.ItemCallback<Character>() {
            override fun areItemsTheSame(oldItem: Character, newItem: Character) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Character, newItem: Character) =
                oldItem == newItem
        }
    }
}