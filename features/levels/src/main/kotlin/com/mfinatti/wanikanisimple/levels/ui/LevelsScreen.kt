package com.mfinatti.wanikanisimple.levels.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun LevelsScreen(
    modifier: Modifier = Modifier,
    onLevelClick: (level: Int) -> Unit = {},
) {
    val tiers: List<LevelTier> = LevelTier.entries
    LazyVerticalGrid(
        columns = GridCells.Fixed(5),
        modifier = modifier.fillMaxWidth().padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        tiers.forEach { tier ->
            item(span = { GridItemSpan(5) }) {
                Text(stringResource(tier.labelRes))
            }
            items(tier.levels.count()) {
                LevelBox(tier.levels.start + it, onClick = { onLevelClick(it) })
            }
        }
    }
}

@Composable
fun LevelBox(
    level: Int,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    Box(
        modifier = modifier
            .size(48.dp)
            .background(Color.DarkGray, shape = RoundedCornerShape(2.dp))
            .clickable { onClick() },
        contentAlignment = Alignment.Center,
    ) {
        Text(level.toString(), color = Color.White)
    }
}

@Composable
@Preview
fun LevelsScreenPreview() {
    LevelsScreen()
}

@Composable
@Preview
fun LevelBoxPreview() {
    LevelBox(1)
}


