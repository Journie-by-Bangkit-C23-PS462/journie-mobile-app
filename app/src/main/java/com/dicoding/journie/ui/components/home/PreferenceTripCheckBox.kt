package com.dicoding.journie.ui.components.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dicoding.journie.ui.theme.JournieTheme

@Composable
fun PreferenceTripCheckBox(
    label: String,
    preference: Boolean,
    onPreferenceStateChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 4.dp, bottom = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    )
    {
        Checkbox(
            checked = preference,
            onCheckedChange = onPreferenceStateChange,
            colors = CheckboxDefaults.colors(Color.DarkGray)
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(text = label)
    }
}

@Preview(showBackground = true)
@Composable
fun PreferenceTripCheckboxPreview() {
    JournieTheme {
        var checked by remember { mutableStateOf(false) }
        PreferenceTripCheckBox(
            label = "Tes",
            preference = checked,
            onPreferenceStateChange = {
                checked = it
            }
        )
    }
}