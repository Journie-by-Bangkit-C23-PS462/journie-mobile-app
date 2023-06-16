package com.dicoding.journie.ui.screen

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.FloatingActionButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dicoding.journie.PlaceRecommendationActivity
import com.dicoding.journie.R
import com.dicoding.journie.data.local.dummyDestination
import com.dicoding.journie.data.navigation.Screen
import com.dicoding.journie.data.network.response.ActivePlanResponse
import com.dicoding.journie.ui.components.home.DestinationCard
import com.dicoding.journie.ui.components.home.SectionTitle
import com.dicoding.journie.ui.theme.JournieTheme
import com.dicoding.journie.viewmodel.DestinationViewModel
import com.dicoding.journie.viewmodel.DestinationViewModelFactory

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    username: String,
    navController: NavHostController
) {
    val context = LocalContext.current as Activity
    val destinationViewModel : DestinationViewModel = viewModel(factory = DestinationViewModelFactory.getInstance())
    val activePlanData by destinationViewModel.activePlanData.collectAsState(initial = emptyList())
    val activePlanStatus by destinationViewModel.activePlanStatus.collectAsState(initial = false)
    destinationViewModel.getActivePlan(username)
    Box(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 15.dp, end = 15.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.End
        ) {
            FloatingActionButton(
                onClick = {
                          navController.navigate(Screen.CreatePlan.route)
                },
                backgroundColor = Color.Black,
                contentColor = Color.Yellow,
                shape = RoundedCornerShape(5.dp),
                elevation = FloatingActionButtonDefaults.elevation(defaultElevation = 2.dp)
            ) {
                Row(modifier = Modifier.padding(start = 12.dp, end = 12.dp),verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = Icons.Filled.Add, contentDescription = "new plan icon", tint = Color.Yellow)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Rencana Baru",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp,
                        color = Color.Yellow
                    )
                }
            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(start = 25.dp, top = 12.dp, end = 25.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.fillMaxHeight()
            ) {
                Text(
                    text = "Selamat datang,",
                    color = Color.Gray,
                    fontSize = 14.sp
                )
                Text(
                    text = "${username}!",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
            Image(
                painter = painterResource(R.drawable.journie_logo),
                contentDescription = "Journie Logo",
                modifier = Modifier
                    .size(height = 20.dp, width = 78.dp)
            )
        }
        Column(
            modifier = Modifier
                .padding(25.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Image(
                painter = painterResource(id = R.drawable.banner),
                modifier = Modifier
                    .size(width = 340.dp, height = 168.dp),
                contentDescription = "Banner",
            )
        }
        Column {
            SectionTitle(title = "Kota Populer Saat Ini")
            Spacer(modifier = Modifier.height(12.dp))
            LazyRow(
                state = rememberLazyListState(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(horizontal = 25.dp),
            ) {
                items(dummyDestination) {
                    DestinationCard(
                        title = it.name,
                        subtitle = it.subname,
                        urlImage = it.urlImage,
                        modifier = Modifier
                    )
                }
            }
            Spacer(modifier = Modifier.height(25.dp))
            SectionTitle(title = "Rekomendasi Aktif")
            Spacer(modifier = Modifier.height(12.dp))
            if (activePlanData != null) {
                if (activePlanData!!.isNotEmpty() && activePlanStatus == true) {
                    LazyRow(
                        state = rememberLazyListState(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        contentPadding = PaddingValues(horizontal = 25.dp),
                    ) {
                        items(activePlanData!!.indices.toList()) { index ->
                            val planData = activePlanData!!.getOrNull(index)
                            planData?.let { plan ->
                                if (plan.isNotEmpty()) {
                                    val city = plan[0][0].city.toString()
                                    DestinationCard(
                                        title = city,
                                        subtitle = "Rencana Piknik ke-${index + 1}",
                                        urlImage = getCityImage(city),
                                        modifier = Modifier,
                                        onClick = {

                                            val activePlanResponse = ActivePlanResponse(
                                                data = activePlanData,
                                                status = activePlanStatus
                                            )
                                            context.startActivity(
                                                Intent(context, PlaceRecommendationActivity::class.java).apply {
                                                    putExtra(PlaceRecommendationActivity.EXTRA_LIST_DESTINATION_PLAN, activePlanResponse)
                                                    putExtra(PlaceRecommendationActivity.EXTRA_INDEX, index)
                                                }
                                            )
                                        }
                                    )
                                }
                            }
                        }
                    }
                } else {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 25.dp, end = 25.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "Tidak Ada Rekomendasi yang aktif")
                    }
                }
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 25.dp, end = 25.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Tidak Ada Rekomendasi yang aktif")
                }
            }
        }
    }
}

fun getCityImage(city: String) : String {
    return when ( city ) {
        "Jakarta" -> "https://th.bing.com/th/id/R.e70e26ad920880ca0e7d9afceff7cee6?rik=U2WVPDmd%2bOM2Sg&riu=http%3a%2f%2fblog.reservasi.com%2fwp-content%2fuploads%2f2015%2f08%2fTidak-Liburan-Ke-Luar-Kota-Ide-Staycation-Dan-Liburan-Di-Jakarta-Ini-Akan-Sangat-Membantu-Kalian.jpg&ehk=eB%2bSd1G7jxyo1pXhvaVb1VoB%2blKyktQ5rrc53mlXMVU%3d&risl=&pid=ImgRaw&r=0"
        "Bandung" -> "https://th.bing.com/th/id/R.8e0fb11dc04dd053d6cdbcd52dc9bcc6?rik=iD%2foSogxC9oC2g&riu=http%3a%2f%2f2.bp.blogspot.com%2f-DLWPx3Ldj_A%2fVcTOMJ6iwjI%2fAAAAAAAABUY%2fh9c_lfakEos%2fs1600%2fsate.jpg&ehk=5ImaTipkL%2fcjJK%2fTtXOQ4SIHtRIGRk8hU89wPbchHqQ%3d&risl=&pid=ImgRaw&r=0"
        "Semarang" -> "https://th.bing.com/th/id/R.69fed3ef6116c66422a4ff6d27f5bdd0?rik=F0YhO%2fgpwNK2Vw&riu=http%3a%2f%2f1.bp.blogspot.com%2f-YBS0Te_L27A%2fUWltwNzfPwI%2fAAAAAAAAAHM%2fTYyORcaCO84%2fs1600%2fMonumen%2bSemarang.jpg&ehk=WBnStMOQypdlDVvi27giChrzqnru7liXv3W0jjOdm4w%3d&risl=&pid=ImgRaw&r=0"
        "Surabaya" -> "https://th.bing.com/th/id/OIP.BzUA9stjk0d3FSzP3jIPLwHaEb?pid=ImgDet&rs=1"
        "Yogyakarta" -> "https://balistarisland.com/wp-content/uploads/2016/09/yogyakartacity1.jpg"
        else -> ""
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    JournieTheme {
        HomeScreen(username = "Orang Ganteng", navController = rememberNavController())
    }
}