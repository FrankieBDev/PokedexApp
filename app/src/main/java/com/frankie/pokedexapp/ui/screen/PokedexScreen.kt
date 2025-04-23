package com.frankie.pokedexapp.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.frankie.pokedexapp.ui.viewModel.PokedexUiState
import com.frankie.pokedexapp.ui.viewModel.PokedexViewModel

@Preview
@Composable
fun PokedexScreen(viewModel: PokedexViewModel = viewModel()) {
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(100.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when (uiState) {
            is PokedexUiState.Success -> {
                LazyColumn(modifier = Modifier.fillMaxWidth()) {
                    items((uiState as PokedexUiState.Success).pokemonList) { pokemon ->
                        Text(text = pokemon, modifier = Modifier.padding(8.dp))
                    }
                }
            }
            is PokedexUiState.Loading -> {
                CircularProgressIndicator()
            }
            is PokedexUiState.Error -> {
                val errorMessage = (uiState as PokedexUiState.Error).message
                Text(text = errorMessage, color = Color.Red)
            }
        }

        Button(onClick = { viewModel.fetchPokemonList() }) {
            Text("Load Pokemon")
        }
    }
}