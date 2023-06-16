package com.dicoding.journie.ui.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dicoding.journie.ui.theme.JournieTheme

@Composable
fun PrimaryButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    enabled : Boolean = true,
    content : @Composable() (RowScope.() -> Unit)
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp),
        content = content,
        elevation = ButtonDefaults.elevation(1.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun LoginButtonPreview() {
    JournieTheme {
    }
}