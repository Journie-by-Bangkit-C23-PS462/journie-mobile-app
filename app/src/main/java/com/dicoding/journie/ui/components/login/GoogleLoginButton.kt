package com.dicoding.journie.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dicoding.journie.R
import com.dicoding.journie.ui.theme.JournieTheme

@Composable
fun GoogleLoginButton(
    modifier: Modifier = Modifier
) {
    OutlinedButton(
        onClick = {},
        border = BorderStroke(2.dp, MaterialTheme.colors.secondary),
        modifier = modifier
            .fillMaxWidth()
            .height(52.dp)
    ) {
//        Image(
//            painter = painterResource(R.drawable.google),
//            contentDescription = "Google Logo",
//            modifier = Modifier
//                .size(30.dp)
//                .padding(end = 8.dp)
//        )
        Text(
            text = stringResource(R.string.login_google),
            color = MaterialTheme.colors.secondary,
            modifier = Modifier
                .align(Alignment.CenterVertically),
            style = MaterialTheme.typography.subtitle2.copy(
                fontWeight = FontWeight.Bold
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GoogleLoginButtonPreview() {
    JournieTheme{
        GoogleLoginButton()
    }
}