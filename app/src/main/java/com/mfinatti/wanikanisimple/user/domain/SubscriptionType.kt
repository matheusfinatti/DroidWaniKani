package com.mfinatti.wanikanisimple.user.domain

enum class SubscriptionType(val maxLevel: Level) {
    free(Level.UnsubscribedMaxLevel),
    recurring(Level.SubscribedMaxLevel),
    lifetime(Level.SubscribedMaxLevel);
}
