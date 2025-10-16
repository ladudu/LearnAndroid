package com.example.unitconverter

import android.os.Bundle
import android.widget.Space
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.unitconverter.ui.theme.UnitConverterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UnitConverterTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    UnitConverter(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun UnitConverter(modifier: Modifier = Modifier){
    Column(modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Row {
            Text(text = "Hello World")
        }
        Spacer(modifier = Modifier.padding(10.0.dp))
        OutlinedTextField(value = "", onValueChange = {

        },modifier = Modifier.fillMaxWidth())
        Row {
            val context = LocalContext.current
            Button(onClick = {
                Toast.makeText(context, "Hello World", Toast.LENGTH_SHORT).show()
            }) {
                Text("ClickMe")
            }
        }
        Row {
            Box{
              Button(onClick = {}) {
                  Text("Select")
                  Icon(Icons.Default.ArrowDropDown, contentDescription = null)
              }
                DropdownMenu( expanded = false, onDismissRequest = {}) {
                    DropdownMenuItem(
                        text = { Text("Unit 3") },
                        onClick = {}
                    )
                    DropdownMenuItem(
                        text = { Text("Unit 4") },
                        onClick = {}
                    )
                }
            }
            Spacer(modifier = Modifier.padding(10.0.dp))
            Box{
                Button(onClick = {}) {
                    Text("Select")
                    Icon(Icons.Default.ArrowDropDown, contentDescription = null)
                }
                DropdownMenu( expanded = false, onDismissRequest = {}) {
                    DropdownMenuItem(
                        text = { Text("Unit 1") },
                        onClick = {}
                    )
                    DropdownMenuItem(
                        text = { Text("Unit 2") },
                        onClick = {}
                    )
                }
            }
        }
    }
}



@Preview
@Composable
fun UnitConverterPreview(){
    UnitConverter( );
}