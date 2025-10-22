package com.example.myrecipeapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.myrecipeapp.MainViewModel.RecipeState

@Composable
fun RecipeScreen(
    modifier: Modifier = Modifier,
    viewState : RecipeState,
    navigateToDetailScreen: (Category) -> Unit
){

    Box(
        modifier = modifier.fillMaxSize()
    ){
        when{
            viewState.isLoading ->{
                CircularProgressIndicator(modifier.fillMaxSize().align(Alignment.Center))
            }

            viewState.error != null ->{
                Text(text ="Error Occurred")
            }
            else ->{
                CategoryScreen(categories = viewState.recipes,navigateToDetailScreen)
            }
        }
    }
}

@Composable
fun CategoryScreen(
    categories: List<Category>,
    navigateToDetailScreen: (Category) -> Unit
){
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize()
    ){
        items(categories) {
            category ->
            CategoryItem(category = category, navigateToDetailScreen )
        }
    }
}

@Composable
fun CategoryItem(
    category: Category,
    navigateToDetailScreen: (Category) -> Unit
){
    Column(
        modifier = Modifier.padding(8.dp).fillMaxSize().clickable{
            navigateToDetailScreen(category)
        },
        horizontalAlignment = Alignment.CenterHorizontally,

    ){
        Image(
            painter = rememberAsyncImagePainter( category.strCategoryThumb),
            contentDescription = null,
            modifier = Modifier.fillMaxSize().aspectRatio(1f)
        )
        Text(
            text = category.strCategory,
            color = Color.Black,
            modifier = Modifier.padding(top = 4.dp),
            style = TextStyle(fontWeight = FontWeight.Bold),
        )
    }
}