package com.example.captaingame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.captaingame.ui.theme.CaptainGameTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CaptainGameTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CaptainGame(Modifier.padding(innerPadding))
                }
            }
        }
    }


}
@Composable
fun CaptainGame(modifier: Modifier = Modifier) {
    var treasuresFound by remember { mutableStateOf(0) }
    val direction = remember { mutableStateOf("North") }
    val stormOrTreasure = remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Treasures Found: ${treasuresFound}")
        Text(text = "Current Direction: ${direction.value}")
        Text(text = " ${stormOrTreasure.value}")
        Button(onClick = {
            direction.value = "East"
            if (Random.nextBoolean()){
                treasuresFound += 1
                stormOrTreasure.value = "WE FOUND A Treasure!!!"
            }else{
                stormOrTreasure.value = "STORM AHEAD!!!"
            }
        }) {
            Text("Sail East")
        }
        Button(onClick = {
            direction.value = "West"
            if (Random.nextBoolean()){
                treasuresFound += 1
                stormOrTreasure.value = "WE FOUND A Treasure!!!"
            }else{
                stormOrTreasure.value = "STORM AHEAD!!!"
            }
        }) {
            Text("Sail West")
        }
        Button(onClick = {
            direction.value = "North"
            if (Random.nextBoolean()){
                treasuresFound += 1
                stormOrTreasure.value = "WE FOUND A Treasure!!!"
            }else{
                stormOrTreasure.value = "STORM AHEAD!!!"
            }
        }) {
            Text("Sail North")
        }
        Button(onClick = {
            direction.value = "South"
            if (Random.nextBoolean()){
                treasuresFound += 1
                stormOrTreasure.value = "WE FOUND A Treasure!!!"
            }else{
                stormOrTreasure.value = "STORM AHEAD!!!"
            }
        }) {
            Text("Sail South")
        }
    }

}
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview()
@Composable
fun CaptainGamePreview() {
    CaptainGameTheme {
        CaptainGame(Modifier.padding())
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CaptainGameTheme {
        Greeting("Android")
    }
}