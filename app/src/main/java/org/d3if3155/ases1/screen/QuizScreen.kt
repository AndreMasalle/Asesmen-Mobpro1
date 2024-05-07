package org.d3if3155.ases1.screen

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.d3if3155.ases1.R
import org.d3if3155.ases1.ui.theme.Ases1Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuizScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack()}) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = stringResource(id = R.string.kembali),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }

                },
                title = {
                    Text(text = stringResource(id = R.string.course1))
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    ) {padding ->
        SoalContent(Modifier.padding(padding))
    }
}




@Composable
fun SoalContent(modifier: Modifier) {

    val radioOptions1 = listOf(
        stringResource(id = R.string.s1o1),
        stringResource(id = R.string.s1o2),
        stringResource(id = R.string.s1o3),
        stringResource(id = R.string.s1o4),
    )
    val radioOptions2 = listOf(
        stringResource(id = R.string.s2o1),
        stringResource(id = R.string.s2o2),
        stringResource(id = R.string.s2o3),
        stringResource(id = R.string.s2o4),
    )

    var jawaban1 by remember { mutableStateOf("") }
    var jawaban2 by remember { mutableStateOf("") }
    var poin by remember { mutableIntStateOf(0) }
    var jawab by remember { mutableStateOf(false) }
    val context = LocalContext.current

    Column(
        modifier =
        modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card {
            Text(
                text = stringResource(id = R.string.pertanyaan1),
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.fillMaxWidth()
            )
            RadioButtonComponent1(pilihan1 = { jawaban1 = it })
        }
        Card{
            Image(
                painter = painterResource(id = R.drawable.soaldua) ,
                contentDescription = "Soal 2",
                modifier = Modifier
                    .align(alignment = Alignment.CenterHorizontally)
                    .padding(8.dp)

            )
            Text(
                text = stringResource(id = R.string.pertanyaan2),
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp)
            )
            RadioButtonComponent2(pilihan2 = {jawaban2 = it})
        }
        Button(
            onClick = {
                // Ketika jawaban1 atau jawaban2 kosong maka tampil teks"harus diselesaikan"
                // Ketika button ditekan, maka akan membagikan nilai
                // Kedua soal harus diisi
                jawab = (jawaban1.isNotBlank() && jawaban2.isNotBlank())
                if(!jawab){
                    Toast.makeText(context, "Kedua soal harus dijawab", Toast.LENGTH_SHORT).show()
                    return@Button
                }
                poin = getPoin(jawaban1==radioOptions1[0],jawaban2==radioOptions2[3])


                      },
            contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp)
        ) {
            Text(text = stringResource(id =R.string.selesai))
        }
        if (jawab){
            Divider(modifier = Modifier.padding(vertical = 8.dp),
                thickness = 1.dp
            )
            Text(
                text = stringResource(id = R.string.hasil,poin),
                style = MaterialTheme.typography.titleLarge
            )
            Button(
                onClick = {
                    shareData(
                        context = context,
                        message = context.getString(R.string.bagikan_template,poin.toString())

                    )
                },
                modifier =Modifier.padding(top = 8.dp),
                contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp)
            ) {
                Text(text = stringResource(id = R.string.bagikan))
            }
        }


    }
}

private fun getPoin(jawaban1: Boolean, jawaban2: Boolean): Int {
    return if (jawaban1 && jawaban2) {
        100
    } else if (jawaban1 || jawaban2) {
        50
    } else {
        0
    }
}


private fun shareData(context: Context, message: String ) {
    val shareIntent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, message)
    }
    if (shareIntent.resolveActivity(context.packageManager)!= null){
        context.startActivity(shareIntent)
    }
}


@Composable
fun RadioButtonComponent1(pilihan1: (String) -> Unit) {
    val radioOptions1 = listOf(
        stringResource(id = R.string.s1o1),
        stringResource(id = R.string.s1o2),
        stringResource(id = R.string.s1o3),
        stringResource(id = R.string.s1o4),
    )
    val (selectedOption, onOptionSelected) = remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Column {
            radioOptions1.forEach { text ->
                Row(
                    Modifier
                        .fillMaxWidth()
                        .selectable(
                            selected = (text == selectedOption),
                            onClick = {
                                onOptionSelected(text)
                                pilihan1(text)
                            }
                        )
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val context = LocalContext.current
                    RadioButton(
                        selected = (text == selectedOption),
                        modifier = Modifier
                            .padding(all = Dp(value = 8F)),
                        onClick = {
                            onOptionSelected(text)
                            pilihan1(text)
                            Toast.makeText(context, text, Toast.LENGTH_LONG).show()
                        }
                    )
                    Text(
                        text = text,
                        modifier = Modifier.padding(start = 16.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun RadioButtonComponent2(pilihan2: (String) -> Unit) {
    val radioOptions2 = listOf(
        stringResource(id = R.string.s2o1),
        stringResource(id = R.string.s2o2),
        stringResource(id = R.string.s2o3),
        stringResource(id = R.string.s2o4),
    )
    val (selectedOption, onOptionSelected) = remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        radioOptions2.forEach { text ->
            Row(
                Modifier
                    .fillMaxWidth()
                    .selectable(
                        selected = (text == selectedOption),
                        onClick = {
                            onOptionSelected(text)
                            pilihan2(text)
                        }
                    )
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                val context = LocalContext.current
                RadioButton(
                    selected = (text == selectedOption),
                    modifier = Modifier
                        .padding(all = Dp(value = 8F)),
                    onClick = {
                        onOptionSelected(text)
                        pilihan2(text)
                        Toast.makeText(context, text, Toast.LENGTH_LONG).show()
                    }
                )
                Text(
                    text = text,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
        }
    }
}



@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun QuizPreview() {
    Ases1Theme {
        QuizScreen(rememberNavController())
    }
}