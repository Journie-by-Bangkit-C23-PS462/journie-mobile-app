package com.dicoding.journie.ui.screen

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dicoding.journie.R
import com.dicoding.journie.RegisterActivity
import com.dicoding.journie.ui.components.EmailInput
import com.dicoding.journie.ui.components.GoogleLoginButton
import com.dicoding.journie.ui.components.PrimaryButton
import com.dicoding.journie.ui.components.PasswordInput
import com.dicoding.journie.ui.theme.JournieTheme

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier
) {
    LoginContent()
}

@Composable
fun LoginContent(
    modifier: Modifier = Modifier
) {
    var emailState by remember { mutableStateOf("") }
    var passwordState by remember { mutableStateOf("") }
    val context = LocalContext.current
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .weight(1f)
                .padding(top = 80.dp, start = 25.dp, end = 25.dp)

        ) {
            Image(
                painter = painterResource(R.drawable.journie_logo),
                contentDescription = "Journie Logo",
                modifier = Modifier
                    .size(height = 48.dp, width = 208.dp)
            )
            Column(
                modifier = Modifier
                    .padding(top = 15.dp)
            ) {
                Text(
                    text = stringResource(R.string.welcome),
                    style = MaterialTheme.typography.h5.copy(
                        fontWeight = FontWeight.ExtraBold
                    ),
                    modifier = Modifier
                        .padding(top = 30.dp)
                )
                Text(
                    text = stringResource(R.string.login_msg),
                    style = MaterialTheme.typography.subtitle1.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier
                        .padding(top = 8.dp)
                )
                Spacer(modifier = Modifier.height(15.dp))
                EmailInput(
                    label = "Email",
                    placeholder = "example@mail.com",
                    value = emailState,
                    onValueChange = {
                        emailState = it
                    }
                )
                PasswordInput(
                    label = "Kata Sandi",
                    placeholder = "**********",
                    value = passwordState,
                    onValueChange = {
                        passwordState = it
                    }
                )
            }
            PrimaryButton(
                modifier = Modifier
                    .padding(top = 15.dp),
                onClick = {
    //                    TODO: Insert login logic here
                },
                label = "Masuk"
            )
            TextButton(
                onClick = {
                    val intent = Intent(context, RegisterActivity::class.java)
                    context.startActivity(intent)
                },
                colors = ButtonDefaults.buttonColors(Color.Black)
            ) {
                Text(
                    text = stringResource(R.string.register_rec),
                    style = MaterialTheme.typography.subtitle2,
                    modifier = Modifier
                        .padding(top = 15.dp)
                )
            }
        }
        Text(
            text = stringResource(R.string.copyright),
            style = MaterialTheme.typography.caption.copy(
                fontWeight = FontWeight.Medium
            ),
            color = Color.LightGray,
            modifier = Modifier
                .padding(bottom = 16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LoginContentPreview() {
    JournieTheme {
        LoginContent()
    }
}