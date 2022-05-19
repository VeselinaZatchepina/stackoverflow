package common.models

import common.EMPTY_STRING

@JvmInline
value class RequestId(private val id: String) {
    fun asString() = id

    companion object {
        val NONE = RequestId(EMPTY_STRING)
    }
}