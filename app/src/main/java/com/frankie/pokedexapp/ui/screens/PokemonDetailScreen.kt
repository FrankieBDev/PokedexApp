package com.frankie.pokedexapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.frankie.pokedexapp.ui.viewModel.PokedexViewModel


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
            containerColor = Color.Transparent,
            bottomBar = {
                BottomAppBar(
                    containerColor = Color.Transparent,
                    tonalElevation = 0.dp,
                    modifier = Modifier.padding(start = 16.dp, bottom = 16.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .background(
                                color = Color.Black.copy(alpha = 0.7f),
                                shape = CircleShape
                            )
                            .clickable { navController.popBackStack() }
                            .padding(horizontal = 12.dp, vertical = 8.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back",
                                tint = Color.White
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(text = "Back", color = Color.White)
                        }
                    }
                }
            }
        ) { paddingValues ->

            Box(modifier = Modifier.fillMaxSize().background(Color.Red)) {
                AsyncImage(
                    model = "https://w0.peakpx.com/wallpaper/75/47/HD-wallpaper-pokedex-red-pokemon.jpg",
                    contentDescription = null,
                    modifier = Modifier
                        .size(900.dp)
                        .align(Alignment.BottomStart),
                    contentScale = ContentScale.Crop
                )

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .padding(horizontal = 24.dp)
                        .offset(y = 200.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = Color.White.copy(alpha = 0.9f),
                        shadowElevation = 8.dp,
                        modifier = Modifier
                            .widthIn(min = 280.dp, max = 360.dp)
                            .padding(vertical = 8.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            AsyncImage(
                                model = pokemon.sprites.frontDefault,
                                contentDescription = "${pokemon.name} sprite",
                                modifier = Modifier.size(200.dp)
                            )

                            Spacer(modifier = Modifier.height(12.dp))

                            Text(
                                text = pokemon.name.replaceFirstChar { it.uppercase() },
                                fontFamily = FontFamily.Monospace,
                                style = MaterialTheme.typography.headlineSmall,
                                color = Color.Black
                            )
                        }
                    }

                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = Color.White.copy(alpha = 0.9f),
                        shadowElevation = 8.dp,
                        modifier = Modifier
                            .widthIn(min = 200.dp, max = 300.dp)
                            .padding(vertical = 8.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "# ${pokemon.id}",
                                fontFamily = FontFamily.Monospace,
                                color = Color.Black,
                                style = MaterialTheme.typography.bodyLarge
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            Text(
                                text = "Type: ${pokemon.types.joinToString(", ") { it.type.name.replaceFirstChar { it.uppercase() } }}",
                                fontFamily = FontFamily.Monospace,
                                color = Color.Black,
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }
                    }

                }
            }
            }
        }
    }