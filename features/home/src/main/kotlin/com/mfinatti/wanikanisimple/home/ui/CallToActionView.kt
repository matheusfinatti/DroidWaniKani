package com.mfinatti.wanikanisimple.home.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mfinatti.wanikanisimple.home.R
import com.mfinatti.wanikanisimple.theme.Kanji
import com.mfinatti.wanikanisimple.theme.Radical
import com.mfinatti.wanikanisimple.theme.WaniKaniButton

@Composable
fun CallToActionBox(
    primaryColor: Color,
    @DrawableRes image: Int,
    numberOfItems: Int,
    dateText: String?,
    typeOfWork: String,
    description: String,
    buttonText: String,
    onButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    CompositionLocalProvider(
        LocalTextStyle provides LocalTextStyle.current.copy(color = Color.White),
        LocalContentColor provides primaryColor
    ) {
        Box(
            modifier = modifier
                .background(color = primaryColor, shape = RoundedCornerShape(4.dp))
                .padding(24.dp)
        ) {
            Column {
                Row {
                    Column(modifier.weight(0.5f)) {
                        Text(dateText ?: "")
                        Row {
                            Text(
                                typeOfWork,
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp,
                            )
                            Spacer(modifier = Modifier.size(8.dp))
                            Text(
                                text = "$numberOfItems",
                                fontWeight = FontWeight.Bold,
                                color = primaryColor,
                                modifier = Modifier
                                    .background(color = Color.White, shape = RoundedCornerShape(16.dp))
                                    .padding(vertical = 2.dp, horizontal = 6.dp)
                            )
                        }
                        Spacer(modifier = Modifier.size(8.dp))
                        Text(description)
                    }
                    Column(modifier.weight(0.3f)) {
                        Image(painterResource(image), contentDescription = null)
                    }
                }
                Spacer(modifier = Modifier.size(16.dp))
                Row(modifier = Modifier.fillMaxWidth()) {
                    WaniKaniButton(modifier = Modifier.fillMaxWidth(), onClick = onButtonClick) {
                        Text(buttonText, color = primaryColor)
                    }
                }
            }
        }
    }
}

@Composable
@Preview
fun LessonsPreview() {
    CallToActionBox(
        primaryColor = Kanji,
        image = R.drawable.feature_home_lesson,
        numberOfItems = 9,
        dateText = "Today",
        typeOfWork = "Lessons",
        description = "We cooked up these lessons just for you.",
        buttonText = "Start Lessons",
        onButtonClick = {}
    )
}

@Composable
@Preview
fun ReviewsPreview() {
    CallToActionBox(
        primaryColor = Radical,
        image = R.drawable.feature_home_review,
        numberOfItems = 19,
        dateText = "",
        typeOfWork = "Reviews",
        description = "Review these items to level them up!",
        buttonText = "Start Reviews",
        onButtonClick = {}
    )
}