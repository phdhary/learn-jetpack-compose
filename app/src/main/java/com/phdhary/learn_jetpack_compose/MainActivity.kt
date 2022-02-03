package com.phdhary.learn_jetpack_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val color = remember {
                mutableStateOf(Color.Cyan)
            }
            Column(modifier = Modifier.fillMaxSize()) {
                ColorBox(
                    Modifier
                        .weight(1f)
                        .fillMaxSize(),
                ) {
                    color.value = it
                }
                Box(
                    modifier = Modifier
                        .background(color.value)
                        .weight(1f)
                        .fillMaxSize()
                )
            }
        }
    }
}


@Preview
@Composable
fun Showd() {
//    ColorBox(modifier = Modifier.fillMaxSize())
}

@Composable
fun ColorBox(modifier: Modifier, updateColor: (Color) -> Unit) {
    Box(
        modifier = modifier
            .background(Color.LightGray)
            .clickable {
                updateColor(
                    Color(
                        Random.nextInt(),
                        Random.nextInt(),
                        Random.nextInt(),
                    )
                )
            }
    )
}