package com.example.a30_days_of_movement

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.a30_days_of_movement.model.MovementsRepository
import com.example.a30_days_of_movement.ui.theme.A30DaysOfMovementTheme

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
        modifier = modifier
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

        val movements = MovementsRepository.movements
            MovementList(
                movements = movements,
                contentPadding = padding,
            )
        }
}

