# Pokedex App

### TL:DR;

A simple Android app I built using Jetpack Compose, designed to connect with a custom backend: [PokeBFF](https://github.com/FrankieBDev/PokeBFF). 

Created as a learning exercise to better understand how frontends interact with a BFF and manage UI state.

---

### Tech Stack

- Kotlin
- Jetpack Compose (UI)
- Retrofit + OkHttp (networking)  
- Gson (JSON parsing)
- Coil (image loading)
- Navigation Compose
- MVVM Architecture
---

### How to Run It Locally

To run the Pokédex app locally, you'll need Android Studio set up along with either an emulator or a connected device.

#### Prerequisites

- Android Studio (Giraffe or newer recommended)
- Android SDK 33+
- A running emulator or physical Android device

#### Steps
1. Clone the repository:  
    git clone https://github.com/FrankieBDev/PokedexApp
2. Open the project in Android Studio.
3. Let Gradle sync and install dependencies.
4. Start an emulator or plug in your device.
5. Press Run to build and launch the app.  

The app will connect to the BFF at http://10.0.2.2:8080 if you're running it via an emulator.

---

### What It Does

This app fetches and displays a paginated list of Pokémon, with the ability to view additional details for each one. It's structured using MVVM architecture and uses Compose for UI, with Coil handling image loading and Retrofit managing network calls.

## Displays a scrollable list of Pokémon

![](https://lh7-rt.googleusercontent.com/docsz/AD_4nXe199POvqFGBwiYC4ITsxe6TRefxkvbvOwoY00fa_5HjJ7-9tbJxgnDcQ8RxDDtfQh-tttC_KCgzsVXS-OgWtHL04BNm75wvaGkJHdomAJLQkmYAb9o3sY23HQLXl7-bxhQv1T1TQ?key=YQUPdVJ-3B6eN1GN9HiTmw)

## Loads additional Pokémon as you scroll

![](https://lh7-rt.googleusercontent.com/docsz/AD_4nXcsGYw9GJ-U3yUdyrRimDwTB9Izsh5PZqmqxutJXye5AtMfSkA9vBdgL1r2h68dJLZb5pj168gzHAtgz1ARdv26eti7RdLMj4YW9vOiDINti0VSe0iU94NLtgelE6rFxsrxsfvE?key=YQUPdVJ-3B6eN1GN9HiTmw)

## Shows a detailed view with sprite, name, ID, and types.  Plus navigation.

![](https://lh7-rt.googleusercontent.com/docsz/AD_4nXcqeKfTCxs00Hi_ANICHcrmO1Sk5i52qruGN6ru0ZU82aobyhuwhwD8DPvlMkcyoGmWQsCR2HFAE03xwVWXN2kKsQ8hLhUb8LUZ2Axj0nXosLDpD67M7Ze1W7QqtISHvn3tGkw5?key=YQUPdVJ-3B6eN1GN9HiTmw)

## Includes loading indicators and basic error states

![](https://lh7-rt.googleusercontent.com/docsz/AD_4nXemshq9KK_VOXJhzjn3FKDwSBQbuCwzjsKFxDxHlU5a2xPi3XCOwryzdsJOcYuuQgE9ZqKTrrBx2eSm7C432OFmxtOQU_DSbcK1OZUsKbspkOrdGYizc14-KQ_LokS9QmlfWlyG9A?key=YQUPdVJ-3B6eN1GN9HiTmw)

---

### Project Structure Overview

#### UI
- MainActivity.kt  
    App entry point, sets up theming and navigation.  
    
- PokedexScreen.kt  
    Main list screen (with lazy loading and error states)  
    
- PokemonDetailScreen.kt  
    Detail view with sprite and type information  
    
- NavGraph.kt  
    Navigation logic between screens  

#### ViewModel

- PokedexViewModel.kt  
    Controls state and manages repository interaction  
    
- PokedexUiState  
    Defines states: Loading, Success, Error

#### Data Layer

- PokemonRepository.kt  
    Handles data fetching via Retrofit

#### Networking

- PokemonApiService.kt  
    Retrofit endpoints  
      
    
- RetrofitClient.kt  
    Sets up Retrofit instance with logging and base URL  

#### Models

- PokemonResponse.kt  
    Summary data for list items  
    
- PokemonDetailResponse.kt  
    Detail model for the expanded view  

#### Tests

This project includes unit tests for PokedexViewModel, which handles all data loading and UI state management.

Currently tested:

- fetchPokemonList() returns a Success state with mocked Pokémon data.
- fetchMorePokemon() appends new results and updates the list.
- Errors during data fetch correctly trigger the Error state.  


These tests use mockk to mock the repository and StandardTestDispatcher to control coroutine timing. 
This allows isolated testing of logic and state changes without hitting the real API.
