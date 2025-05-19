package com.frankie.pokedexapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.frankie.pokedexapp.ui.navigation.NavGraph
import com.frankie.pokedexapp.ui.screens.PokedexScreen
import com.frankie.pokedexapp.ui.theme.PokedexAppTheme
import com.frankie.pokedexapp.ui.viewModel.PokedexViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PokedexAppTheme {
                val navController = rememberNavController()
                val viewModel: PokedexViewModel = viewModel()
                NavGraph(navController = navController, viewModel = viewModel)
                }
            }
        }
    }
