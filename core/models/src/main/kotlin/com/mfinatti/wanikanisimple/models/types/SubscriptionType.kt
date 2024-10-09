package com.mfinatti.wanikanisimple.models.types

enum class SubscriptionType(val maxLevel: Level) {
    free(Level.UnsubscribedMaxLevel),
    recurring(Level.SubscribedMaxLevel),
    lifetime(Level.SubscribedMaxLevel);
}
