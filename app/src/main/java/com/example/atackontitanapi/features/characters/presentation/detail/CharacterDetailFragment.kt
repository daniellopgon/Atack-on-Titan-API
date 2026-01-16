package com.example.atackontitanapi.features.characters.presentation.detail

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import coil3.load
import coil3.request.crossfade
import com.example.atackontitanapi.R
import com.example.atackontitanapi.core.presentation.errors.ErrorAppFactory
import com.example.atackontitanapi.databinding.FragmentCharacterDetailBinding
import com.example.atackontitanapi.features.characters.domain.Character
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharacterDetailFragment : Fragment(R.layout.fragment_character_detail) {

    private var _binding: FragmentCharacterDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CharacterDetailViewModel by viewModel()
    private val errorFactory by lazy { ErrorAppFactory(requireContext()) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentCharacterDetailBinding.bind(view)

        observeState()
    }

    private fun observeState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    renderState(state)
                }
            }
        }
    }

    private fun renderState(state: CharacterDetailUiState) {
        binding.progressBar.isVisible = state.isLoading
        binding.detailContent.isVisible = state.showContent

        if (state.error != null) {
            binding.detailContent.isVisible = false
            val errorUI = errorFactory.build(state.error) { viewModel.retry() }
            binding.errorView.render(errorUI)
        } else {
            binding.errorView.hide()
        }

        state.character?.let { character ->
            bindCharacter(character)
        }
    }

    private fun bindCharacter(character: Character) {
        binding.characterImage.load(character.img) {
            crossfade(true)
        }
        binding.nameText.text = character.name
        binding.aliasText.text = character.alias.joinToString(", ")
        binding.speciesText.text = character.species.joinToString(", ")
        binding.genderText.text = character.gender
        binding.ageText.text = character.age
        binding.heightText.text = character.height
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
