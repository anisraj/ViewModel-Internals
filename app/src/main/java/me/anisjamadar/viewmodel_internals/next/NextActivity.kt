package me.anisjamadar.viewmodel_internals.next

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import me.anisjamadar.viewmodel_internals.MyApplication
import me.anisjamadar.viewmodel_internals.ui.theme.ViewModelInternalsTheme
import me.anisjamadar.viewmodel_internals.viewmodel.MyViewModel
import me.anisjamadar.viewmodel_internals.viewmodel.ViewModelFactory

class NextActivity : ComponentActivity() {

    private lateinit var viewModel: NextViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = (application as MyApplication)
            .getViewModel(
                NextViewModel::class.java,
                ViewModelFactory {
                    NextViewModel(NextRepository())
                }
            ) as NextViewModel

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
            (application as MyApplication).clearViewModel(NextViewModel::class.java as Class<MyViewModel>)
        }
    }
}

@Composable
fun Greeting(
    modifier: Modifier = Modifier,
    viewModel: NextViewModel
) {
    val count by viewModel.counter.collectAsStateWithLifecycle()
    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceAround,
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
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ViewModelInternalsTheme {
        Greeting(viewModel = NextViewModel(NextRepository()))
    }
}