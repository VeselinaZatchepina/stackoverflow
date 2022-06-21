package common.helpers

import common.context.SContext
import common.models.SError
import common.models.SState

fun Throwable.asSError(
    code: String = "unknown",
    group: String = "exceptions",
    message: String = this.message ?: "",
) = SError(
    code = code,
    group = group,
    field = "",
    message = message,
    exception = this,
)

fun SContext.addError(error: SError) = errors.add(error)
fun SContext.fail(error: SError) {
    addError(error)
    state = SState.FAILING
}

