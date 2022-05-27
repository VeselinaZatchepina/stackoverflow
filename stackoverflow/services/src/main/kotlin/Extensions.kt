import common.context.SContext
import common.models.SError
import common.models.SState

fun SContext.errorResponse(buildError: () -> SError, error: (SError) -> SError) = apply {
    state = SState.FAILING
    errors.add(error(buildError()))
}

fun SContext.successResponse(context: SContext.() -> Unit) = apply(context)
    .apply { state = SState.RUNNING }

val notFoundError: (String) -> String = { "Not found ad by id $it" }