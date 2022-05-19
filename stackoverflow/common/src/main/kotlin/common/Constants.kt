package common

import kotlinx.datetime.Instant

const val EMPTY_STRING = ""

private val INSTANT_NONE = Instant.fromEpochMilliseconds(Long.MIN_VALUE)
val Instant.Companion.NONE
    get() = INSTANT_NONE