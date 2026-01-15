package com.example.atackontitanapi.features.characters.presentation.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil3.load
import coil3.request.crossfade
import com.example.atackontitanapi.databinding.ItemCharacterBinding
import com.example.atackontitanapi.features.characters.domain.Character

class CharacterAdapter(
    private val onItemClick: (Character) -> Unit
) : ListAdapter<Character, CharacterAdapter.ViewHolder>(CharacterDiffCallback()) {

    class ViewHolder(private val binding: ItemCharacterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(character: Character, onItemClick: (Character) -> Unit) {
            binding.nameText.text = character.name
            binding.speciesText.text = character.species.joinToString(", ")
            binding.avatarImage.load(character.img) {
                crossfade(true)
            }
            binding.root.setOnClickListener { onItemClick(character) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCharacterBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), onItemClick)
    }

    private class CharacterDiffCallback : DiffUtil.ItemCallback<Character>() {
        override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem == newItem
        }
    }
}
