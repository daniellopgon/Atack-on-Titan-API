package com.example.atackontitanapi.features.characters.presentation.list

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.atackontitanapi.R
import com.example.atackontitanapi.core.presentation.errors.ErrorAppFactory
import com.example.atackontitanapi.databinding.FragmentCharacterListBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharacterListFragment : Fragment(R.layout.fragment_character_list) {

    private var _binding: FragmentCharacterListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CharacterListViewModel by viewModel()
    private lateinit var adapter: CharacterAdapter
    private val errorFactory by lazy { ErrorAppFactory(requireContext()) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentCharacterListBinding.bind(view)

        setupRecyclerView()
        observeState()
    }

    private fun setupRecyclerView() {
        adapter = CharacterAdapter(
            onItemClick = { character -> navigateToDetail(character.id) }
        )
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = adapter
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

    private fun renderState(state: CharacterListUiState) {
        binding.progressBar.isVisible = state.isLoading

        if (state.error != null) {
            binding.recyclerView.isVisible = false
            val errorUI = errorFactory.build(state.error) { viewModel.retry() }
            binding.errorView.render(errorUI)
        } else {
            binding.errorView.hide()
        }

        if (state.showContent) {
            binding.recyclerView.isVisible = true
            adapter.submitList(state.characters)
        }

        binding.emptyView.isVisible = state.showEmpty
    }

    private fun navigateToDetail(characterId: Int) {
        val action = CharacterListFragmentDirections
            .actionCharacterListFragmentToCharacterDetailFragment(characterId)
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
