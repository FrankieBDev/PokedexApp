package com.frankie.pokedexapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
    Box(modifier = Modifier.fillMaxSize().background(Color.Red)) {
        AsyncImage(
            model = "https://w0.peakpx.com/wallpaper/75/47/HD-wallpaper-pokedex-red-pokemon.jpg",
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        when (uiState) {
            is PokedexUiState.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        CircularProgressIndicator()
                        Spacer(modifier = Modifier.height(8.dp))
                        Text("Loading Pokemon...")
                    }
                }
            }

            is PokedexUiState.Success -> {
                val pokemonList = (uiState as PokedexUiState.Success).pokemonList
                LazyColumn(
                    modifier = Modifier
                        .padding(top = 158.dp, bottom = 92.dp)
                ) {
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
}

    @Composable
    fun PokemonListItem(
        pokemon: PokemonResponse,
        onClick: () -> Unit
        ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onClick() }
                .padding(start = 92.dp, end = 16.dp, top = 8.dp, bottom = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(96.dp)
                    .clip(CircleShape)
                    .background(Color.White),
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    model = pokemon.imageUrl,
                    contentDescription = "${pokemon.name} image",
                    modifier = Modifier
                        .size(100.dp),
                    error = painterResource(android.R.drawable.ic_menu_gallery)
                )
            }
            Spacer(modifier = Modifier.width(10.dp))

                Text(
                    text = pokemon.name.replaceFirstChar { it.uppercase() },
                    fontSize = 26.sp,
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(start = 10.dp)
                )
            }
        }



