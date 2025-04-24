package com.frankie.pokedexapp.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.frankie.pokedexapp.ui.viewModel.PokedexViewModel

@Preview
@Composable
fun PokedexScreen(viewModel: PokedexViewModel = viewModel()) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchPokemonList()
    }

    // UI restructure in progress



@Composable
fun PokemonItem() {
    }
}