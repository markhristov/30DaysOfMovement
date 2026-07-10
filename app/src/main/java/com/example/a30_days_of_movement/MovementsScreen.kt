package com.example.a30_days_of_movement

import android.content.res.Configuration
import androidx.annotation.ArrayRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.a30_days_of_movement.model.Movement
import com.example.a30_days_of_movement.model.MovementsRepository
import com.example.a30_days_of_movement.ui.theme.A30DaysOfMovementTheme

@Composable
fun MovementList(
    movements: List<Movement>, modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
) {
    val visibleState = remember {
        MutableTransitionState(false).apply {
            // Start the animation immediately.
            targetState = true
        }
    }

    AnimatedVisibility(
        visibleState = visibleState,
        enter = fadeIn(
            animationSpec = spring(dampingRatio = Spring.DampingRatioLowBouncy)
        ),
        exit = fadeOut(),
        modifier = modifier
    ) {
        LazyColumn(contentPadding = contentPadding) {
            itemsIndexed(movements) { index, movement ->
                MovementCard(
                    movement = movement,
                    day = index + 1,
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        // Animate each list item to slide in vertically
                        .animateEnterExit(
                            enter = slideInVertically(
                                animationSpec = spring(
                                    stiffness = Spring.StiffnessVeryLow,
                                    dampingRatio = Spring.DampingRatioLowBouncy
                                ),
                                initialOffsetY = { it * (index + 1) } // staggered entrance
                            )
                        )
                )
            }
        }
    }
}


@Composable
fun MovementImage(@DrawableRes image: Int, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
    ) {
        Image(
            painter = painterResource(id = image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
        )
    }
}

@Composable
fun MovementCues(@ArrayRes cues: Int, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val cueList = context.resources.getStringArray(cues)
    Column(modifier = modifier.padding(start = 24.dp, end = 24.dp, top = 16.dp, bottom = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)) {
        cueList.forEach { cue ->
            Text(text = "• $cue",
                style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@Composable
fun MovementCard(movement: Movement, day: Int, modifier: Modifier = Modifier) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = modifier
            .clickable { expanded = !expanded }
    ) {
        Column(
            modifier = Modifier.animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioNoBouncy,
                    stiffness = Spring.StiffnessMediumLow
                )
            )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .sizeIn(minHeight = 72.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                MovementInfo(day, name = movement.name, modifier = Modifier.weight(1f))
                MovementImage(image = movement.image)
            }

            if (expanded) {
                MovementCues(cues = movement.cues)
            }
        }
    }
}

@Composable
private fun MovementInfo(day: Int, @StringRes name: Int, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(text = "Day $day",
            style = MaterialTheme.typography.bodySmall)
        Text(text = stringResource(id = name),
            style = MaterialTheme.typography.headlineMedium)
    }
}


@Preview("Light Theme")
@Preview("Dark Theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun MovementCardPreview() {
    val movement = Movement(
        R.string.movement_downward_dog,
        R.array.downward_dog_cues,
        R.drawable.row_1_column_1
    )
    A30DaysOfMovementTheme {
        MovementCard(movement = movement, day = 1)
    }
}

@Preview("Movement List")
@Composable
fun HeroesPreview() {
    A30DaysOfMovementTheme(darkTheme = false) {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            MovementList(movements = MovementsRepository.movements)
        }
    }
}
