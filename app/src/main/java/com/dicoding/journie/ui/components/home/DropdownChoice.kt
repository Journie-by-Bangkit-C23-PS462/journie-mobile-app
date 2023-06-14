package com.dicoding.journie.ui.components.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.toSize
import com.dicoding.journie.ui.theme.JournieTheme


@Composable
fun DropdownChoice(
    items: List<String>,
    selectedItem: String,
    onItemSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedIndex by remember { mutableStateOf(0) }
    var textFieldValue by remember { mutableStateOf(selectedItem) }

    val icon = if (expanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    var dropdownMenuSize by remember { mutableStateOf(Size.Zero) }

    OutlinedTextField(
        value = textFieldValue,
        onValueChange = { textFieldValue = it },
        modifier = Modifier
            .fillMaxWidth()
            .onGloballyPositioned { coordinates -> dropdownMenuSize = coordinates.size.toSize() },
        trailingIcon = {
            Icon(
                imageVector = icon,
                contentDescription = "icon to show and close dropdown menu",
                modifier = Modifier.clickable(
                    onClick = { expanded = !expanded }
                )
            )
        }
    )
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false },
        modifier = Modifier
            .width(with(LocalDensity.current){dropdownMenuSize.width.toDp()}),
    ) {
        items.forEachIndexed { index, item ->
            DropdownMenuItem(
                onClick = {
                    selectedIndex = index
                    expanded = false
                    textFieldValue = item
                    onItemSelected(item)
                } 
            ) {
                Text(text = item)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DropdownChoicePreview() {
    JournieTheme {
//        var valState by remember { mutableStateOf("-") }
//        val cities = listOf<String>(
//            "Bandung",
//            "Jakarta",
//            "Semarang",
//            "Surabaya",
//            "Yogyakarta"
//        )
//        DropdownChoice(
//            values = cities,
//            valueState = valState,
//            onValueChange = {
//                valState = it
//            }
//        )
    }
}