package com.example.pokedexapp.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pokedexapp.ui.viewModel.PokedexViewModel

@Preview
@Composable
fun PokedexScreen(viewModel: PokedexViewModel = viewModel()) {
    val pokemonList by viewModel.pokemonList.collectAsState()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(100.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = { viewModel.fetchPokemonList() }) {
                Text("Load Pokemon")
            }

            if (pokemonList.isEmpty()) {
                Text("No pokemon found")
            } else {
                LazyColumn {
                    items(pokemonList) { pokemonList ->
                        Text(text = pokemonList, modifier = Modifier.padding(8.dp))
                    }
                }
            }
        }
}