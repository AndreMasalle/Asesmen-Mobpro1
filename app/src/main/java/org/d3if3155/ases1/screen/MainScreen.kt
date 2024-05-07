package org.d3if3155.ases1.screen

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.d3if3155.ases1.R
import org.d3if3155.ases1.navigations.Screen
import org.d3if3155.ases1.ui.theme.Ases1Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.app_name))
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                ),
                actions = {
                    IconButton(onClick = {
                        navController.navigate(Screen.About.route)
                    }) {
                        Icon(
                            imageVector = Icons.Outlined.Info,
                            contentDescription = stringResource(id = R.string.tentang_aplikasi),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(Screen.Score.route)
                }
            ) {
                Icon(
                    painter = painterResource(R.drawable.baseline_scoreboard_24),
                    contentDescription = stringResource(id = R.string.liat_skor),
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
    ) {padding ->
        ScreenContent(Modifier.padding(padding), navController)
    }
}

@Composable
fun CourseContent( navController: NavHostController, onClick: () -> Unit ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onClick() }
                .padding(16.dp)
            ,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.soaldua) ,
                contentDescription = "Matematika"
            )
            Text(
                text = stringResource(id = R.string.course1),
                modifier = Modifier
                    .padding(16.dp),
                textAlign = TextAlign.Center,
            )
            Text(
                text = stringResource(id = R.string.deskripsi1),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis)
            Button(
                onClick = {
                    navController.navigate(Screen.Quiz.route)
                }
            ) {
                Text(text = stringResource(id = R.string.kerjakan))
            }
        }

    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(16.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onClick() }
                .padding(16.dp)
            ,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = stringResource(id = R.string.course2),
                modifier = Modifier
                    .padding(16.dp),
                textAlign = TextAlign.Center,
            )
            Text(
                text = stringResource(id = R.string.deskripsi2),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis)
            Button(onClick = {
                // Belum hadir, sepertinya dia sedang izin
            }) {
                Text(text = stringResource(id = R.string.segera_hadir))
            }
        }

    }
}


@Composable
fun ScreenContent(modifier: Modifier, navController: NavHostController) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(bottom = 84.dp),
    ) {
        item{
            CourseContent(navController) {}
        }
    }
}



@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun GreetingPreview() {
    Ases1Theme {
        MainScreen(rememberNavController())
    }
}