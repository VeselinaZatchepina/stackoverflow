package common.models

import common.EMPTY_STRING

data class SError(
    val code: String = EMPTY_STRING,
    val group: String = EMPTY_STRING,
    val field: String = EMPTY_STRING,
    val message: String = EMPTY_STRING,
    val exception: Throwable? = null,
)