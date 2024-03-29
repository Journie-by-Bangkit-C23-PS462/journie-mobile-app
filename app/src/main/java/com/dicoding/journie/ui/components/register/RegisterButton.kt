package com.dicoding.journie.ui.components.register

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dicoding.journie.R
import com.dicoding.journie.ui.theme.JournieTheme

@Composable
fun RegisterButton(
    modifier: Modifier = Modifier
) {
    Button(
        onClick = { /*TODO*/ },
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp)
    ) {
        Text(
            text = stringResource(id = R.string.register),
            color = MaterialTheme.colors.secondary,
            modifier = Modifier
                .align(Alignment.CenterVertically),
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            letterSpacing = 1.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterButtonPreview() {
    JournieTheme {
        RegisterButton()
    }
}