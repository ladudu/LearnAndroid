package com.example.myrecipeapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun RecipeApp( modifier: Modifier = Modifier,navController: NavHostController){
    val recipeViewModel : MainViewModel = viewModel()
    val viewState by recipeViewModel.categoriesState

    NavHost(navController = navController, startDestination = Screen.RecipeScreen.route){
        composable(route = Screen.RecipeScreen.route){
            RecipeScreen(viewState = viewState, navigateToDetailScreen={
                navController.currentBackStackEntry?.savedStateHandle?.set("recipe", it)
                navController.navigate(Screen.RecipeDetailScreen.route)
            })
        }
        composable(route = Screen.RecipeDetailScreen.route){
            val category = navController.previousBackStackEntry?.savedStateHandle?.
            get<Category>("recipe")?: Category(
                "",
                "",
                "",
                ""
            )
            CategoryDetailScreen(category = category)
        }
    }
}