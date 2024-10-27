package com.mfinatti.wanikanisimple.home.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mfinatti.wanikanisimple.theme.Kanji
import com.mfinatti.wanikanisimple.home.R
import com.mfinatti.wanikanisimple.theme.Radical
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mfinatti.wanikanisimple.home.domain.state.SummaryState
import com.mfinatti.wanikanisimple.models.data.Lesson
import com.mfinatti.wanikanisimple.models.data.Review
import com.mfinatti.wanikanisimple.models.data.Summary
import com.mfinatti.wanikanisimple.models.types.SubjectId
import java.time.Instant

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel<HomeViewModel>(),
    modifier: Modifier = Modifier,
) {
    val summaryState by viewModel.summaryState.collectAsStateWithLifecycle()

    Box(modifier = modifier.fillMaxSize()) {
        when (val state = summaryState) {
            is SummaryState.Error -> Text("Error: ${state.message}")
            SummaryState.Loading -> Text("Loading")
            is SummaryState.Success -> TodaySummary(state.summary)
        }
    }
}

@Composable
fun TodaySummary(
    summary: Summary,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        LessonBox(numberOfLessons = summary.lessons.size)
        Spacer(modifier = Modifier.size(16.dp))
        ReviewBox(numberOfReviews = summary.reviews.size)
    }
}

@Composable
fun LessonBox(
    numberOfLessons: Int,
    modifier: Modifier = Modifier,
) {
    CallToActionBox(
        primaryColor = Kanji,
        image = R.drawable.feature_home_lesson,
        numberOfItems = numberOfLessons,
        dateText = stringResource(id = R.string.feature_home_lesson_today),
        typeOfWork = stringResource(id = R.string.feature_home_lesson_title),
        description = stringResource(id = R.string.feature_home_lesson_desc),
        buttonText = stringResource(id = R.string.feature_home_lesson_button_start),
        onButtonClick = {},
        modifier = modifier,
    )
}

@Composable
fun ReviewBox(
    numberOfReviews: Int,
    modifier: Modifier = Modifier,
) {
    CallToActionBox(
        primaryColor = Radical,
        image = R.drawable.feature_home_review,
        numberOfItems = numberOfReviews,
        dateText = null,
        typeOfWork = stringResource(id = R.string.feature_home_review_title),
        description = stringResource(id = R.string.feature_home_review_desc),
        buttonText = stringResource(id = R.string.feature_home_review_button_start),
        onButtonClick = {},
        modifier = modifier,
    )
}

@Composable
@Preview
fun TodaySummaryPreview() {
    TodaySummary(
        summary = Summary(
            lessons = listOf(Lesson(Instant.now(), subjectIds = listOf(SubjectId.from(1).getOrThrow()))),
            reviews = listOf(Review(Instant.now(), subjectIds = listOf(SubjectId.from(2).getOrThrow()))),
            nextReviewsAt = Instant.now(),
        )
    )
}
