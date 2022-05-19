package common.models

import common.EMPTY_STRING

@JvmInline
value class IdModel(private val id: String) {
    fun asString() = id

    companion object {
        val NONE = IdModel(EMPTY_STRING)
    }
}