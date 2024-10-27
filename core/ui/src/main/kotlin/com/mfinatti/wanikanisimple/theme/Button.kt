package com.mfinatti.wanikanisimple.theme

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun WaniKaniButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    content: @Composable RowScope.() -> Unit = {},
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        shape = RoundedCornerShape(4.dp),
        content = content,
        colors = primaryButtonColors()
    )
}

@Composable
fun WaniKaniOutlinedButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    content: @Composable RowScope.() -> Unit = {},
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        shape = RoundedCornerShape(4.dp),
        border = ButtonDefaults.outlinedButtonBorder(enabled).copy(
            brush = Brush.horizontalGradient(
                colors = listOf(
                    Color.White,
                    Color.White,
                )
            )
        ),
        content = content,
        colors = primaryOutlinedButtonColors()
    )
}

@Composable
fun primaryButtonColors() = ButtonDefaults.buttonColors().copy(
    containerColor = Color.White,
    contentColor = Kanji,
)

@Composable
fun primaryOutlinedButtonColors() = ButtonDefaults.outlinedButtonColors().copy(
    containerColor = Color.Transparent,
    contentColor = Color.White,
)
