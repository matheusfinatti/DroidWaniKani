package com.mfinatti.wanikanisimple.ui

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mfinatti.wanikanisimple.models.data.User
import com.mfinatti.wanikanisimple.models.types.Level
import com.mfinatti.wanikanisimple.models.types.Subscription
import com.mfinatti.wanikanisimple.models.types.SubscriptionType
import com.mfinatti.wanikanisimple.models.types.UserId
import java.time.Instant
import com.mfinatti.wanikanisimple.R
import java.util.UUID

@Composable
fun UserCard(
    user: User?,
    modifier: Modifier = Modifier,
) {
    if (user == null) return

    Column(
        modifier = modifier.padding(16.dp).fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painterResource(R.drawable.default_avatar),
            contentDescription = null,
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
        )
        Spacer(Modifier.size(12.dp))
        Text(user.username, color = Color.DarkGray)
        Spacer(Modifier.size(4.dp))
        Text("Level ${user.level.value} | ${user.subscription.type}", color = Color.DarkGray)
    }
}

@Composable
@Preview
private fun UserCardPreview() {
    UserCard(
        user = User(
            id = UserId.from(UUID.randomUUID().toString()).getOrThrow(),
            username = "Crabinator",
            level = Level.from(
                value = 14,
                subscriptionType = SubscriptionType.lifetime,
            ).getOrThrow(),
            profileUrl = "",
            startedAt = Instant.now(),
            currentVacationStartedAt = null,
            subscription = Subscription.Lifetime(true),
        )
    )
}
