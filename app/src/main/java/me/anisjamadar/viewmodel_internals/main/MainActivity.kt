package me.anisjamadar.viewmodel_internals.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import me.anisjamadar.viewmodel_internals.main.MainViewModel
import me.anisjamadar.viewmodel_internals.MyApplication
import me.anisjamadar.viewmodel_internals.next.NextActivity
import me.anisjamadar.viewmodel_internals.ui.theme.ViewModelInternalsTheme
import me.anisjamadar.viewmodel_internals.viewmodel.MyViewModel

class MainActivity : ComponentActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = (application as MyApplication).getViewModel(MainViewModel::class.java) as MainViewModel

        enableEdgeToEdge()
        setContent {
            ViewModelInternalsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        modifier = Modifier
                            .padding(innerPadding),
                        viewModel
                    )
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (!isChangingConfigurations) {
            (application as MyApplication).clearViewModel(MainViewModel::class.java as Class<MyViewModel>)
        }
    }
}

@Composable
fun Greeting(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel
) {
    val context = LocalContext.current
    val count by viewModel.counter.collectAsStateWithLifecycle()
    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "$count",
                fontSize = 72.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(48.dp))

            Button(
                onClick = { viewModel.increaseCounter() },
                modifier = Modifier.size(width = 200.dp, height = 60.dp)
            ) {
                Text("Increment", fontSize = 20.sp)
            }
        }

        Button(
            onClick = {
                context.startActivity(Intent(context, NextActivity::class.java))
            },
            modifier = Modifier
                .size(width = 200.dp, height = 60.dp)
        ) {
            Text("Next Activity", fontSize = 20.sp)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ViewModelInternalsTheme {
        Greeting(viewModel = MainViewModel())
    }
}