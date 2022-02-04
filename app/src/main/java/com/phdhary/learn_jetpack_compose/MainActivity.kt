package com.phdhary.learn_jetpack_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var percentage by remember {
                mutableStateOf(0f)
            }
            val p = produceState(initialValue = 0f) {
                for (i in 0..100) {
                    delay(300)
                    value = i.toFloat() / 100
                }
            }
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressBar(
                    percentage = p.value,
                    number = 100
                )
                Column(
                    modifier = Modifier
                        .padding(horizontal = 30.dp)
                        .align(Alignment.BottomCenter)
                        .fillMaxHeight(0.4f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Button(onClick = { percentage = 0f }) {
                        Text("Reset")
                    }
                    Slider(
                        value = percentage,
                        onValueChange = {
                            percentage = it
                        },
                    )

                }
            }
        }
    }
}


@Preview(
    showSystemUi = true
)
@Composable
fun TestPrev() {
    var percentage by remember {
        mutableStateOf(0f)
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressBar(
            percentage = percentage,
            number = 100
        )
        Column(
            modifier = Modifier
                .padding(horizontal = 30.dp)
                .align(Alignment.BottomCenter)
                .fillMaxHeight(0.4f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = { percentage = 0f }) {
                Text("Reset")
            }
            Slider(
                value = percentage,
                onValueChange = {
                    percentage = it
                },
            )

        }
    }
}

@Composable
fun CircularProgressBar(
    percentage: Float,
    number: Int,
    fontSize: TextUnit = 28.sp,
    radius: Dp = 50.dp,
    color: Color = Color.Green,
    strokeWidth: Dp = 8.dp,
    animDuration: Int = 300,
    animDelay: Int = 0,
) {
    var animationPlayed by remember {
        mutableStateOf(false)
    }
    val curPercentage = animateFloatAsState(
        targetValue = if (animationPlayed) percentage else 0f,
        animationSpec = tween(
            durationMillis = animDuration,
            delayMillis = animDelay
        )
    )



    LaunchedEffect(key1 = true) {
        animationPlayed = true
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.size(radius * 2f)
    ) {
        Canvas(
            modifier = Modifier.size(radius * 2f)
        ) {
            drawArc(
                color,
                -90f,
                360 * curPercentage.value,
                false,
                style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Butt)
            )
        }
        Text(
            text = (
                    curPercentage.value * number).toInt().toString(),
            color = Color.Black,
            fontSize = fontSize,
            fontWeight = FontWeight.Bold,
        )

    }
}
