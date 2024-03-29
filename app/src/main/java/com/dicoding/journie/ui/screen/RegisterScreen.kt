package com.dicoding.journie.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dicoding.journie.R
import com.dicoding.journie.ui.components.EmailInput
import com.dicoding.journie.ui.components.PasswordInput
import com.dicoding.journie.ui.components.register.NumberInput
import com.dicoding.journie.ui.components.register.TextInput
import com.dicoding.journie.ui.theme.JournieTheme

@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier
) {
    RegisterContent()
}

@Composable
fun RegisterContent(
    modifier: Modifier = Modifier
) {
    var usernameState by remember { mutableStateOf("") }
    var ageState by remember { mutableStateOf("") }
    var isMaleState by remember { mutableStateOf(true) }
    var elementarySchoolState by remember { mutableStateOf("") }
    var emailState by remember { mutableStateOf("") }
    var passwordState by remember { mutableStateOf("") }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(start = 25.dp, end = 25.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.height(90.dp))
        Image(
            painter = painterResource(R.drawable.journie_logo),
            contentDescription = "Journie Logo",
            modifier = Modifier
                .size(width = 207.dp, height = 48.dp)
        )
        Column(
            modifier = Modifier
                .padding(top = 60.dp, bottom = 60.dp)
        ) {
            Text(
                text = stringResource(R.string.welcome_register),
                style = MaterialTheme.typography.h5.copy(
                    fontWeight = FontWeight.ExtraBold
                ),
            )
            Text(
                text = stringResource(R.string.register_msg),
                style = MaterialTheme.typography.subtitle1.copy(
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier
                    .padding(top = 8.dp, bottom = 28.dp)
            )
            TextInput(
                label = "Username",
                placeholder = "Contoh: sandi_123",
                value = usernameState,
                onValueChange = {
                    usernameState = it
                }
            )
            NumberInput(
                label = "Umur",
                placeholder = "Contoh: 25",
                value = ageState,
                onValueChange = {
                    ageState = it
                }
            )
            var onMaleSelected by remember { mutableStateOf(false) }
            var onFemaleSelected by remember { mutableStateOf(false) }
            Column {
                Text(
                    text = "Jenis Kelamin",
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier.height(12.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = onMaleSelected,
                            onClick = {
                                onFemaleSelected = false
                                onMaleSelected = true
                                isMaleState = true
                            })
                        Text(text = "Pria")
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = onFemaleSelected,
                            onClick = {
                                onMaleSelected = false
                                onFemaleSelected = true
                                isMaleState = false
                            })
                        Text(text = "Perempuan")
                    }
                }
                Spacer(modifier = Modifier.height(28.dp))
            }
            TextInput(
                label = "SD Dimana",
                placeholder = "Contoh: SD Negeri Wakanda 1",
                value = elementarySchoolState,
                onValueChange = {
                    elementarySchoolState = it
                }
            )
            EmailInput(
                label = "Email",
                placeholder = "example@mail.com",
                value = emailState,
                onValueChange = {
                    emailState = it
                }
            )
            PasswordInput(
                label = "Password",
                placeholder = "Password will be censored like this ********",
                value = passwordState,
                onValueChange = {
                    passwordState = it
                }
            )
//            PrimaryButton(
//                onClick = {
//                    // TODO: Insert register logic here
//                },
//                modifier = Modifier
//                    .padding(top = 15.dp),
//                label = "Daftarkan Diriku"
//            )
            Spacer(modifier = Modifier.height(15.dp))
            Text(text = stringResource(id = R.string.login_rec), textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(15.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    JournieTheme {
        RegisterContent()
    }
}