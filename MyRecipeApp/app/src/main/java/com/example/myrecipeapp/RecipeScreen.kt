package com.example.myrecipeapp

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun RecipeScreen(modifier: Modifier = Modifier){
    val recipeViewModel : MainViewModel = viewModel()
    val viewstate by recipeViewModel.categoriesState
    Box(
        modifier = modifier.fillMaxSize()
    ){
        when{
            viewstate.isLoading ->{
                CircularProgressIndicator(modifier.fillMaxSize().align(Alignment.Center))
            }

            viewstate.error != null ->{
                Text(text ="Error Occurred")
            }
            else ->{
            }
        }
    }
}

@Composable
fun CategoryScreen(categories: List<Category>){
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize()
    ){

    }
}

@Composable
fun CategoryItem(category: Category){
    Column(
        modifier = Modifier.padding(8.dp).fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = category.strCategory,
            color = Color.Black,
            modifier = Modifier.padding(top = 4.dp),
            style = TextStyle(fontWeight = FontWeight.Bold)
        )
    }
}