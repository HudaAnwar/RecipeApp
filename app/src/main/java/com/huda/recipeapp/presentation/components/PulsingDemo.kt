package com.huda.recipeapp.presentation.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.unit.dp

//
//object PulseAnimationDefinitions{
//    enum class PulseState{
//        INITIAL,FINAL
//    }
//    val pulsePropKey = FloatPropKey("pulseKey")
//}

@Composable
fun PulsingDemo() {
    val color = MaterialTheme.colors.primary

    val infiniteTransition = rememberInfiniteTransition()
    val pulseMagnitude by infiniteTransition.animateFloat(
        initialValue = PulseAnimationDefinitions.PULSE_STATE_INITIAL,
        targetValue = PulseAnimationDefinitions.PULSE_STATE_FINAL,
        animationSpec = infiniteRepeatable(
            animation = tween(500, easing = FastOutLinearInEasing),
            repeatMode = RepeatMode.Restart
        )
    )

//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .height(55.dp)
//            .padding(100.dp)
//            .background(color = Color.LightGray),
//        horizontalArrangement = Arrangement.Center
//    ) {
        Image(
            modifier = Modifier
                .padding(100.dp)
//                .align(Alignment.CenterVertically)
                .height(pulseMagnitude.dp)
                .width(pulseMagnitude.dp),
            imageVector = Icons.Filled.FavoriteBorder, contentDescription = ""
        )
//    }

//    Canvas(
//        modifier = Modifier
//            .fillMaxWidth()
//            .height(55.dp)
//    ) {
//        drawCircle(
//            radius = pulseMagnitude,
//            brush = SolidColor(color)
//        )
//    }
}

object PulseAnimationDefinitions {
    const val PULSE_STATE_INITIAL = 40f
    const val PULSE_STATE_FINAL = 50f
}
