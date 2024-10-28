package com.mfinatti.wanikanisimple.home.data.mapper

import com.mfinatti.wanikanisimple.core.network.data.model.summary.LessonDTO
import com.mfinatti.wanikanisimple.core.network.data.model.summary.ReviewDTO
import com.mfinatti.wanikanisimple.core.network.data.model.summary.SummaryDTO
import com.mfinatti.wanikanisimple.models.data.Lesson
import com.mfinatti.wanikanisimple.models.data.Review
import com.mfinatti.wanikanisimple.models.data.Summary
import com.mfinatti.wanikanisimple.models.types.SubjectId
import java.time.Instant

fun SummaryDTO.toSummary(): Result<Summary> =  runCatching {
    Summary(
        lessons = lessons.map { it.toLesson().getOrThrow() },
        reviews = reviews.map { it.toReview().getOrThrow() },
        nextReviewsAt = nextReviewsAt?.let { Instant.parse(it) },
    )
}

fun LessonDTO.toLesson(): Result<Lesson> = runCatching {
    Lesson(
        availableAt = Instant.parse(availableAt),
        subjectIds = subjectIds.map { SubjectId.from(it).getOrThrow() },
    )
}

fun ReviewDTO.toReview(): Result<Review> = runCatching {
    Review(
        availableAt = Instant.parse(availableAt),
        subjectIds = subjectIds.map { SubjectId.from(it).getOrThrow() },
    )
}
