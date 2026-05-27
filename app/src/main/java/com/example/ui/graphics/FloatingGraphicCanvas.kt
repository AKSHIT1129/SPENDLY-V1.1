package com.example.ui.graphics

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke

@Composable
fun FloatingGraphicCanvas() {
    val infiniteTransition = rememberInfiniteTransition(label = "orbs")
    val bounceY by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 18f,
        animationSpec = infiniteRepeatable(
            animation = tween(2800, easing = EaseInOutSine),
            repeatMode = RepeatMode.Reverse
        ),
        label = "bounce"
    )

    Canvas(modifier = Modifier.fillMaxSize()) {
        val width = size.width
        val height = size.height

        if (width > 0f && height > 0f) {
            val btcX = width * 0.28f
            val btcY = height * 0.45f + bounceY
            val btcRadius = width * 0.24f

            val btcRadialRadius = btcRadius * 1.6f
            if (btcRadialRadius > 0f) {
                drawCircle(
                    brush = Brush.radialGradient(
                        colors = listOf(Color(0xFFDDF247).copy(alpha = 0.13f), Color.Transparent),
                        center = androidx.compose.ui.geometry.Offset(btcX, btcY),
                        radius = btcRadialRadius
                    ),
                    radius = btcRadialRadius,
                    center = androidx.compose.ui.geometry.Offset(btcX, btcY)
                )
            }

            if (btcRadius > 0f) {
                drawCircle(
                    color = Color(0xFF191823),
                    radius = btcRadius,
                    center = androidx.compose.ui.geometry.Offset(btcX, btcY)
                )
                drawCircle(
                    brush = Brush.linearGradient(
                        colors = listOf(Color(0xFFDDF247), Color(0xFF10B981)),
                        start = androidx.compose.ui.geometry.Offset(btcX - btcRadius, btcY - btcRadius),
                        end = androidx.compose.ui.geometry.Offset(btcX + btcRadius, btcY + btcRadius)
                    ),
                    radius = btcRadius,
                    center = androidx.compose.ui.geometry.Offset(btcX, btcY),
                    style = Stroke(width = 6f)
                )

                drawCircle(
                    color = Color(0xFFDDF247).copy(alpha = 0.8f),
                    radius = btcRadius * 0.45f,
                    center = androidx.compose.ui.geometry.Offset(btcX, btcY),
                    style = Stroke(width = 5f)
                )
            }

            val solX = width * 0.72f
            val solY = height * 0.58f - bounceY
            val solRadius = width * 0.20f

            val solRadialRadius = solRadius * 1.5f
            if (solRadialRadius > 0f) {
                drawCircle(
                    brush = Brush.radialGradient(
                        colors = listOf(Color(0xFFC06240).copy(alpha = 0.11f), Color.Transparent),
                        center = androidx.compose.ui.geometry.Offset(solX, solY),
                        radius = solRadialRadius
                    ),
                    radius = solRadialRadius,
                    center = androidx.compose.ui.geometry.Offset(solX, solY)
                )
            }

            if (solRadius > 0f) {
                drawCircle(
                    color = Color(0xFF14131A),
                    radius = solRadius,
                    center = androidx.compose.ui.geometry.Offset(solX, solY)
                )
                drawCircle(
                    brush = Brush.linearGradient(
                        colors = listOf(Color(0xFFC06240), Color(0xFF8B5CF6)),
                        start = androidx.compose.ui.geometry.Offset(solX - solRadius, solY - solRadius),
                        end = androidx.compose.ui.geometry.Offset(solX + solRadius, solY + solRadius)
                    ),
                    radius = solRadius,
                    center = androidx.compose.ui.geometry.Offset(solX, solY),
                    style = Stroke(width = 4f)
                )
            }
        }
    }
}
