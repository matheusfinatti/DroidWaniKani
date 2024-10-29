package com.mfinatti.wanikanisimple.levels.ui

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mfinatti.wanikanisimple.levels.R
import com.mfinatti.wanikanisimple.models.types.SubjectId
import com.mfinatti.wanikanisimple.theme.Kanji
import com.mfinatti.wanikanisimple.theme.Radical
import com.mfinatti.wanikanisimple.theme.Vocabulary

@Composable
fun LevelScreen(
    level: Int,
    modifier: Modifier = Modifier,
) {
    val viewModel = hiltViewModel<LevelViewModel, LevelViewModel.Factory> { factory ->
        factory.create(level)
    }

    val uiState by viewModel.subjectState.collectAsStateWithLifecycle()

    when (val state = uiState) {
        LevelUiModel.Loading -> LevelsLoading(modifier)
        is LevelUiModel.Error -> LevelsError(state.error ?: "Unknown Error", modifier)
        is LevelUiModel.Subjects -> LevelView(state.subjects, modifier)
    }
}

@Composable
fun LevelsLoading(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun LevelsError(
    error: String,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Text(error, color = Color.Red)
    }
}

@Composable
fun LevelView(
    subjects: List<SubjectItem>,
    modifier: Modifier = Modifier,
) {
    val radicals = subjects.filterIsInstance<RadicalItem>()
    val kanji = subjects.filterIsInstance<KanjiItem>()
    val vocabulary = subjects.filterIsInstance<VocabularyItem>()
    val kanaVocabulary = subjects.filterIsInstance<KanaVocabularyItem>()

    LazyVerticalGrid(
        columns = GridCells.Adaptive(64.dp),
        modifier = modifier
            .padding(4.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        if (radicals.isNotEmpty()) {
            item(span = { GridItemSpan(maxLineSpan) }) {
                Text(
                    text = stringResource(R.string.features_levels_box_radicals),
                    modifier = Modifier.padding(start = 12.dp, top = 8.dp),
                    color = Color.LightGray
                )
            }
            items(radicals.size) { i ->
                RadicalBox(radicals[i])
            }
        }
        if (kanji.isNotEmpty()) {
            item(span = { GridItemSpan(maxLineSpan) }) {
                Text(
                    text = stringResource(R.string.features_levels_box_kanji),
                    modifier = Modifier.padding(start = 12.dp, top = 8.dp),
                    color = Color.LightGray
                )
            }
            items(kanji.size) { i ->
                KanjiBox(kanji[i])
            }
        }
        if (vocabulary.isNotEmpty() || kanaVocabulary.isNotEmpty()) {
            item(span = { GridItemSpan(maxLineSpan) }) {
                Text(
                    text = stringResource(R.string.features_levels_box_vocabulary),
                    modifier = Modifier.padding(start = 12.dp, top = 8.dp),
                    color = Color.LightGray
                )
            }
            items(vocabulary.size, span = { GridItemSpan(maxLineSpan) }) { i ->
                VocabularyBox(vocabulary[i])
            }
            items(kanaVocabulary.size, span = { GridItemSpan(maxLineSpan) }) { i ->
                KanaVocabularyBox(kanaVocabulary[i])
            }
        }
    }
}

@Composable
fun RadicalBox(radical: RadicalItem) {
    Box(
        modifier = Modifier
            .size(64.dp)
            .background(color = Radical, shape = RoundedCornerShape(4.dp)),
        contentAlignment = Alignment.Center,
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(radical.characters, color = Color.White, fontSize = 24.sp)
            Text(radical.meaning, color = Color.White, fontSize = 10.sp)
        }
    }
}

@Composable
fun KanjiBox(kanji: KanjiItem) {
    Box(
        modifier = Modifier
            .size(64.dp)
            .background(color = Kanji, shape = RoundedCornerShape(4.dp)),
        contentAlignment = Alignment.Center,
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(kanji.characters, color = Color.White, fontSize = 24.sp)
            Text(kanji.reading, color = Color.White, fontSize = 8.sp)
            Text(kanji.meaning, color = Color.White, fontSize = 8.sp)
        }
    }
}

@Composable
fun VocabularyBox(vocabulary: VocabularyItem) {
    Box(
        modifier = Modifier
            .height(42.dp)
            .fillMaxWidth()
            .background(color = Vocabulary, shape = RoundedCornerShape(4.dp)),
        contentAlignment = Alignment.Center,
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(vocabulary.characters, Modifier.weight(0.5f).padding(4.dp), color = Color.White, fontSize = 20.sp)
            Column(horizontalAlignment = Alignment.End, modifier = Modifier.weight(0.5f).padding(4.dp)) {
                Text(vocabulary.reading, color = Color.White, fontSize = 8.sp)
                Text(vocabulary.meaning, color = Color.White, fontSize = 8.sp)
            }
        }
    }
}

@Composable
fun KanaVocabularyBox(vocabulary: KanaVocabularyItem) {
    Box(
        modifier = Modifier
            .height(42.dp)
            .fillMaxWidth()
            .background(color = Vocabulary, shape = RoundedCornerShape(4.dp)),
        contentAlignment = Alignment.Center,
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(vocabulary.characters, Modifier.weight(0.7f).padding(4.dp), color = Color.White, fontSize = 24.sp)
            Column(horizontalAlignment = Alignment.End, modifier = Modifier.weight(0.3f).padding(4.dp)) {
                Text(vocabulary.meaning, color = Color.White, fontSize = 10.sp)
            }
        }
    }
}


@Composable
@Preview
private fun PreviewRadicalBox() {
    val radical =
        RadicalItem(
            id = SubjectId.from(3).getOrThrow(),
            characters = "気",
            meaning = "Spirit",
        )
    RadicalBox(radical)
}

@Composable
@Preview
private fun PreviewKanjiBox() {
    val kanji =
        KanjiItem(
            id = SubjectId.from(1).getOrThrow(),
            characters = "下",
            meaning = "Below",
            reading = "した"
        )
    KanjiBox(kanji)
}

@Composable
@Preview
fun PreviewVocabularyBox() {
    val vocabulary =
        VocabularyItem(
            id = SubjectId.from(3).getOrThrow(),
            characters = "気",
            meaning = "Spirit",
            reading = "き"
        )
    VocabularyBox(vocabulary)
}

@Composable
@Preview
fun PreviewKanaVocabularyBox() {
    val kanaVocabulary =
        KanaVocabularyItem(
            id = SubjectId.from(1).getOrThrow(),
            characters = "いつ",
            meaning = "When",
        )
    KanaVocabularyBox(kanaVocabulary)
}
