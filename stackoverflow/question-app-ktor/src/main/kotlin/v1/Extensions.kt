package v1

import common.models.SError
import org.stackoverflow.openapi.models.ResponseResult

fun buildError() = SError(
    field = "_", code = ResponseResult.ERROR.value
)