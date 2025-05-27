package com.frankie.pokedexapp

import com.frankie.pokedexapp.data.model.PokemonResponse
import com.frankie.pokedexapp.data.repository.PokemonRepository
import com.frankie.pokedexapp.ui.viewModel.PokedexUiState
import com.frankie.pokedexapp.ui.viewModel.PokedexViewModel
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class PokedexViewModelTest {
    private val testDispatcher = StandardTestDispatcher()
    private lateinit var viewModel: PokedexViewModel
    private lateinit var mockRepository: PokemonRepository

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        mockRepository = mockk()
        viewModel = PokedexViewModel(mockRepository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun  fetchPokemonListReturnsSuccessWithData() = runTest {
        val mockList = listOf(
            PokemonResponse("Pikachu", "url", "Electric"),
            PokemonResponse(    "Bulbasaur", "url", "Grass")
        )

        coEvery { mockRepository.getPokemonList(any(), any()) } returns mockList

        viewModel.fetchPokemonList()
        advanceUntilIdle()

        val state = viewModel.uiState.first()
        assertTrue(state is PokedexUiState.Success)
        assertEquals(2, (state as PokedexUiState.Success).pokemonList.size)
    }

    @Test
    fun fetchMorePokemonReturnsSuccessWithData() = runTest {
        val mockList = listOf(
            PokemonResponse("Pikachu", "url", "Electric"),
            PokemonResponse(    "Bulbasaur", "url", "Grass")
        )

        coEvery { mockRepository.getPokemonList(any(), any()) } returns mockList

        viewModel.fetchMorePokemon()
        advanceUntilIdle()

        val state = viewModel.uiState.first()
        assertTrue(state is PokedexUiState.Success)
        assertEquals(2, (state as PokedexUiState.Success).pokemonList.size)
    }

    }

// refactor above to align with newly refactored viewModel

//    @Test
//    fun getPokemonDetailLoadsCorrectDetails() = runTest {
//
//    }


//}