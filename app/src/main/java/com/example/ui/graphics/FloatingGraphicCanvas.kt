package com.example.ui.graphics

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.graphics.drawscope.rotate

@Composable
fun FloatingGraphicCanvas() {
    val infiniteTransition = rememberInfiniteTransition(label = "rotating_finance_rings")

    // Rotation angle animations (outer/inner clockwise, middle counter-clockwise)
    val outerRotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(8000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "outer_rotation"
    )

    val middleRotation by infiniteTransition.animateFloat(
        initialValue = 360f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(6000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "middle_rotation"
    )

    val innerRotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(4000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "inner_rotation"
    )

    // Subtle vertical bouncing transition for the center
    val bounceY by infiniteTransition.animateFloat(
        initialValue = -10f,
        targetValue = 10f,
        animationSpec = infiniteRepeatable(
            animation = tween(3000, easing = EaseInOutSine),
            repeatMode = RepeatMode.Reverse
        ),
        label = "canvas_bounce"
    )

    // Pulsing alpha for the radial gradient glow
    val pulseAlpha by infiniteTransition.animateFloat(
        initialValue = 0.4f,
        targetValue = 0.8f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = EaseInOutSine),
            repeatMode = RepeatMode.Reverse
        ),
        label = "glow_pulse"
    )

    Canvas(modifier = Modifier.fillMaxSize()) {
        val width = size.width
        val height = size.height

        if (width > 0f && height > 0f) {
            val centerX = width / 2f
            val centerY = (height / 2f) + bounceY

            // 1. Glowing radial background behind the rings
            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(
                        Color(0xFF6366F1).copy(alpha = 0.22f * pulseAlpha),
                        Color(0xFFC06240).copy(alpha = 0.12f * pulseAlpha),
                        Color.Transparent
                    ),
                    center = Offset(centerX, centerY),
                    radius = width * 0.45f
                ),
                radius = width * 0.45f,
                center = Offset(centerX, centerY)
            )

            // Define ring properties
            val outerRadius = width * 0.32f
            val middleRadius = width * 0.24f
            val innerRadius = width * 0.16f

            // 2. Draw outer ring: BrandLime with rotating gradient stroke
            // We use static or angle-dependent sweep gradients for active glow effect
            val outerGradient = Brush.sweepGradient(
                colors = listOf(
                    Color(0xFFE2F163),
                    Color(0xFFE2F163).copy(alpha = 0.1f),
                    Color(0xFFE2F163)
                ),
                center = Offset(centerX, centerY)
            )

            withTransform({
                rotate(degrees = outerRotation, pivot = Offset(centerX, centerY))
            }) {
                drawCircle(
                    brush = outerGradient,
                    radius = outerRadius,
                    center = Offset(centerX, centerY),
                    style = Stroke(width = 12f)
                )
                // Draw some stylized indicator dots along the orbit to stand out
                drawCircle(
                    color = Color(0xFFE2F163),
                    radius = 6f,
                    center = Offset(centerX + outerRadius, centerY)
                )
            }

            // 3. Draw middle ring: SunsetOrangeGlow with reversed rotating gradient stroke
            val middleGradient = Brush.sweepGradient(
                colors = listOf(
                    Color(0xFFC06240),
                    Color(0xFFC06240).copy(alpha = 0.1f),
                    Color(0xFFC06240)
                ),
                center = Offset(centerX, centerY)
            )

            withTransform({
                rotate(degrees = middleRotation, pivot = Offset(centerX, centerY))
            }) {
                drawCircle(
                    brush = middleGradient,
                    radius = middleRadius,
                    center = Offset(centerX, centerY),
                    style = Stroke(width = 10f)
                )
                drawCircle(
                    color = Color(0xFFC06240),
                    radius = 5f,
                    center = Offset(centerX - middleRadius, centerY)
                )
            }

            // 4. Draw inner ring: Indigo with rotating gradient stroke
            val innerGradient = Brush.sweepGradient(
                colors = listOf(
                    Color(0xFF6366F1),
                    Color(0xFF6366F1).copy(alpha = 0.1f),
                    Color(0xFF6366F1)
                ),
                center = Offset(centerX, centerY)
            )

            withTransform({
                rotate(degrees = innerRotation, pivot = Offset(centerX, centerY))
            }) {
                drawCircle(
                    brush = innerGradient,
                    radius = innerRadius,
                    center = Offset(centerX, centerY),
                    style = Stroke(width = 8f)
                )
                drawCircle(
                    color = Color(0xFF6366F1),
                    radius = 4f,
                    center = Offset(centerX, centerY + innerRadius)
                )
            }
        }
    }
}
