package com.example.a30_days_of_movement

import android.os.Bundle
import android.text.method.MovementMethod
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.a30_days_of_movement.model.Movement
import com.example.a30_days_of_movement.model.MovementsRepository
import com.example.a30_days_of_movement.ui.theme.A30DaysOfMovementTheme
import com.example.a30_days_of_movement.ui.theme._30_days_of_movementTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            A30DaysOfMovementTheme {
                MovementApp()
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovementTopBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(R.string.app_name),
                style = MaterialTheme.typography.displaySmall,
            )
        },
        modifier = Modifier
    )
}

@Preview(showBackground = true)
@Composable
fun MovementAppPreview() {
    A30DaysOfMovementTheme {
        MovementApp()
    }
}

@Composable
fun MovementApp() {
    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {
            MovementTopBar()
        }) { padding ->
            MovementList(
                movements = MovementsRepository.movements,
                modifier = Modifier.padding(padding),
            )
        }
}

@Composable
fun MovementList(movements: List<Movement>, modifier: Modifier = Modifier) {
    TODO("Not yet implemented")
}