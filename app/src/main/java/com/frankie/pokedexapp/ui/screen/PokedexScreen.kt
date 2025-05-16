package com.frankie.pokedexapp.ui.screen

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.frankie.pokedexapp.data.model.PokemonResult
import com.frankie.pokedexapp.ui.viewModel.PokedexUiState
import com.frankie.pokedexapp.ui.viewModel.PokedexViewModel

@Composable
fun PokedexScreen(viewModel: PokedexViewModel = viewModel()) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchPokemonList()
    }

    when (uiState) {
        is PokedexUiState.Loading -> {
            Text("Loading Pokemon...")
        }

        is PokedexUiState.Success -> {
            val list = (uiState as PokedexUiState.Success).pokemonList
            LazyColumn {
                items(list) { pokemon ->
                    Text(pokemon.name)
                }
            }
        }

        is PokedexUiState.Error -> {
            Text(text = (uiState as PokedexUiState.Error).message)
        }

    }

    @Composable
    fun PokemonList(pokemonList: List<PokemonResult>) {
        Text("Fetched ${pokemonList.size} Pokémon")
        LazyColumn {
            items(pokemonList) { pokemon ->
                Text(
                    text = pokemon.name.replaceFirstChar { it.uppercase() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
            }
        }

    }
}


