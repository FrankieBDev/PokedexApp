package com.frankie.pokedexapp.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.frankie.pokedexapp.data.model.PokemonResponse
import com.frankie.pokedexapp.ui.viewModel.PokedexUiState
import com.frankie.pokedexapp.ui.viewModel.PokedexViewModel

@Composable
fun PokedexScreen(
    viewModel: PokedexViewModel,
    onPokemonClick: (String) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchPokemonList()
    }

    when (uiState) {
        is PokedexUiState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
                ) {
                CircularProgressIndicator()
                Text("Loading Pokemon...")
            }
        }

        is PokedexUiState.Success -> {
            val pokemonList = (uiState as PokedexUiState.Success).pokemonList
            LazyColumn {
                items(pokemonList) { pokemon ->
                    PokemonListItem(pokemon = pokemon, onClick = {
                        onPokemonClick(pokemon.name)
                    })
                }
            }
        }

        is PokedexUiState.Error -> {
            Text("Something went wrong")
        }
    }
}

    @Composable
    fun PokemonListItem(
        pokemon: PokemonResponse,
        onClick: () -> Unit
        ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onClick() }
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = pokemon.imageUrl,
                contentDescription = "${pokemon.name} image",
                modifier = Modifier
                    .size(96.dp)
                    .padding(bottom = 8.dp),
                error = painterResource(android.R.drawable.ic_menu_gallery)

            )
            Text(text = pokemon.name.replaceFirstChar { it.uppercase() })
        }
}


