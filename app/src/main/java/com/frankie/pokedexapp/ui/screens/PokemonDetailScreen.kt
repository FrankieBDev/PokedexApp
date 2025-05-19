package com.frankie.pokedexapp.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.frankie.pokedexapp.ui.viewModel.PokedexViewModel
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonDetailScreen(
    name: String,
    navController: NavController,
    viewModel: PokedexViewModel = viewModel()
) {
    LaunchedEffect(name) {
        viewModel.getPokemonDetail(name)
    }

    val detail by viewModel.pokemonDetail.observeAsState()

    if (detail == null) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
        val pokemon = detail!!
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = "Back to Pokedex")},
                    navigationIcon = {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                        }
                    }
                )
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AsyncImage(
                        model = pokemon.sprites.frontDefault,
                        contentDescription = "${pokemon.name} sprite",
                        modifier = Modifier.size(128.dp)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = pokemon.name.replaceFirstChar { it.uppercase() },
                        style = MaterialTheme.typography.headlineSmall
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(text = "ID: ${pokemon.id}")

                    Spacer(Modifier.height(8.dp))

                    Text(
                        text = "Type: ${pokemon.types.joinToString(",") { it.type.name.replaceFirstChar { it.uppercase() } }}"
                    )
                }
            }
        }
    }
}