package com.example.myrecipeapp


sealed class Screen(val route: String) {
    object RecipeScreen : Screen("recipe")
    object RecipeDetailScreen : Screen("recipe_detail")
}

