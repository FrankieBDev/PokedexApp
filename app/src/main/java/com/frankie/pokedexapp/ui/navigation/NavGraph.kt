package com.frankie.pokedexapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.frankie.pokedexapp.ui.viewModel.PokedexViewModel
import com.frankie.pokedexapp.ui.screens.PokedexScreen
import com.frankie.pokedexapp.ui.screens.PokemonDetailScreen

@Composable
fun NavGraph(
    navController: NavHostController,
    viewModel: PokedexViewModel
) {
    NavHost(navController = navController, startDestination = "pokedexScreen") {
        composable("pokedexScreen") {
            PokedexScreen(
                viewModel = viewModel,
                onPokemonClick = { name ->
                    navController.navigate("pokemonDetail/$name")
                }
            )
        }
        composable("pokemonDetail/{name}") { backStackEntry ->
            val name = backStackEntry.arguments?.getString("name") ?: return@composable
            PokemonDetailScreen(
                name = name, viewModel = viewModel,
                navController = navController
            )
        }
    }
}