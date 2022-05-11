package common.models

import java.util.*

@JvmInline
value class IdModel(private val id: String) {
    constructor(id: UUID) : this(id.toString())

    companion object {
        val NONE = IdModel("")
    }

    fun asString() = id

    fun asUUID(): UUID = UUID.fromString(id)
}
