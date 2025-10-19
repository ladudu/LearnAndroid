package com.example.shoppinglist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.CloseSegment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlin.math.log

class ShoppingList {
}
data class ShoppingItem(
    val id: Int,
    var name: String,
    var quantity: Int,
    var isEditing: Boolean = false
){
    override fun toString(): String {
        return "$name ($quantity)"
    }
}
@Composable
fun ShoppingListItemEditor(item: ShoppingItem, onEditComplete: (String, Int) -> Unit){
    var editedName by remember { mutableStateOf(item.name) }
    var editedQuantity by remember { mutableStateOf(item.quantity.toString()) }
    var isEditing by remember { mutableStateOf(item.isEditing) }

    Row (
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ){
        Column {
            BasicTextField(
                value = editedName,
                onValueChange = {
                    editedName = it
                },
                modifier = Modifier
                    .wrapContentWidth()
                    .padding(8.dp),
                singleLine = true
            )
            BasicTextField(
                value = editedQuantity,
                onValueChange ={
                    editedQuantity = it
                },
                modifier = Modifier
                    .wrapContentWidth()
                    .padding(8.dp),
                singleLine = true
            )
        }
        Button(
            onClick = {
                isEditing = false
                onEditComplete(editedName, editedQuantity.toIntOrNull() ?: 0)
            }

        ){
            Text(text = "Save")
        }
    }

}
@Composable
fun ShoppingListItem(
    item: ShoppingItem,
    onEdit: () -> Unit,
    onDelete: () -> Unit
){
    Row(modifier = Modifier
        .padding(8.dp)
        .fillMaxWidth()
        .border(
            border = BorderStroke(2.dp, Color(0xFF018786)),
            shape = RoundedCornerShape(20)
        ),
        horizontalArrangement = Arrangement.SpaceBetween
    )

    {
        Text(text = item.name, modifier = Modifier.padding(8.dp))
        Text(text = "Quantity: ${item.quantity}", modifier = Modifier.padding(8.dp))
        Row(modifier = Modifier.padding(8.dp)){
            IconButton(onClick = onEdit) {
                Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit")
            }
            IconButton(onClick = onDelete) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete")
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingListApp(innerPadding: PaddingValues){
    val sItems  = remember { mutableStateListOf<ShoppingItem>() }
    var showDialog by remember { mutableStateOf(false) }

    var itemName by remember { mutableStateOf("") }
    var itemQuantity by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top
    ) {
        Button(
            onClick = {
                showDialog = true
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Add item")
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
        )
        {
            items(
                items = sItems,
                key = { item -> item.id }
            ) {
                item ->
                if (item.isEditing) {
                    ShoppingListItemEditor(item = item, onEditComplete = { name, quantity ->
                        // 替换为新对象来完成编辑
                        val index = sItems.indexOf(item)
                        if (index != -1) {
                            sItems[index] = item.copy(name = name, quantity = quantity, isEditing = false)
                        }
                    })
                }else{
                    ShoppingListItem(
                        item = item,
                        onEdit = {
                            // 替换为新对象来开始编辑
                            val index = sItems.indexOf(item)
                            if (index != -1) {
                                sItems[index] = item.copy(isEditing = true)
                            }
                        },
                        onDelete = {
                            sItems.remove(item)
                        }
                    )
                }
            }
        }
    }

    if(showDialog){
        // --- 正确的方式 ---
        AlertDialog(
            onDismissRequest = { showDialog = false },
            // 1. 添加一个确认按钮，让用户可以交互
            dismissButton = {
                Button(
                    onClick = {
                        // 在这里处理添加商品的逻辑，然后关闭对话框
                        showDialog = false
                    }
                ) {
                    Text("Cancel")
                    itemName = ""
                    itemQuantity = ""
                }
            },
            confirmButton = {
                Button(onClick = {
                    if (itemName.isNotBlank() && itemQuantity.isNotBlank()) {
                        sItems.add(ShoppingItem(sItems.size + 1, itemName, itemQuantity.toInt()))
                        itemName = ""
                        itemQuantity = ""
                    }
                    showDialog = false
                }) {
                    Text("Add")
                }

            },
            // 2. 提供一个标题
            title = { Text("Add Shopping Item") },
            // 3. 将你的内容（未来会是输入框）放在 text 参数里
            text = {
                Column {
                    OutlinedTextField(
                        value = itemName,
                        onValueChange = {
                            itemName = it
                        },
                        label = {
                            Text("Name")
                        },
                        singleLine = true
                    )
                    OutlinedTextField(
                        value = itemQuantity,
                        onValueChange = {
                            itemQuantity = it
                        },
                        label = {
                            Text("Quantity")
                        },
                        singleLine = true
                    )
                }
            }
        )

    }
}