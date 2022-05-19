import common.context.SContext
import common.models.*
import org.stackoverflow.openapi.models.*

fun SContext.toTransportQuestion(): IResponse = when (val cmd = command) {
    SCommand.CREATE -> toTransportCreate()
    SCommand.READ -> toTransportRead()
    SCommand.UPDATE -> toTransportRead()
    SCommand.DELETE -> toTransportRead()
    SCommand.SEARCH -> toTransportRead()
    SCommand.NONE -> throw UnknownCommand(cmd)
}

fun SContext.toTransportCreate() = CreateQuestionResponse(
    requestId = this.requestId.asString().takeIf { it.isNotBlank() },
    result = if (state == SState.RUNNING) ResponseResult.SUCCESS else ResponseResult.ERROR,
    errors = errors.toTransportErrors(),
    createdQuestion = questionResponse.toTransport()
)

fun SContext.toTransportRead() = GetQuestionResponse(
    requestId = this.requestId.asString().takeIf { it.isNotBlank() },
    result = if (state == SState.RUNNING) ResponseResult.SUCCESS else ResponseResult.ERROR,
    errors = errors.toTransportErrors(),
    question = questionResponse.toTransport()
)

fun SContext.toTransportUpdate() = UpdateQuestionResponse(
    requestId = this.requestId.asString().takeIf { it.isNotBlank() },
    result = if (state == SState.RUNNING) ResponseResult.SUCCESS else ResponseResult.ERROR,
    errors = errors.toTransportErrors(),
    question = questionResponse.toTransport()
)

fun SContext.toTransportDelete() = DeleteQuestionResponse(
    requestId = this.requestId.asString().takeIf { it.isNotBlank() },
    result = if (state == SState.RUNNING) ResponseResult.SUCCESS else ResponseResult.ERROR,
    errors = errors.toTransportErrors(),
    question = questionResponse.toTransport()
)

fun SContext.toTransportSearch() = SearchQuestionResponse(
    requestId = this.requestId.asString().takeIf { it.isNotBlank() },
    result = if (state == SState.RUNNING) ResponseResult.SUCCESS else ResponseResult.ERROR,
    errors = errors.toTransportErrors(),
    questions = questionsResponse.map { it.toTransport() }
)

private fun QuestionModel.toTransport() = ResponseQuestion(
    id = id.takeIf { it != IdModel.NONE }?.asString(),
    title = title,
    text = text,
    rating = rating,
    ownerId = ownerId,
    creationDate = creationDate.toString(),
    status = status.toTransport()
)

private fun QuestionStatusModel.toTransport() = enumValueOf<QuestionStatus>(name)

private fun List<SError>.toTransportErrors(): List<Error>? = this
    .map { it.toTransportAd() }
    .toList()
    .takeIf { it.isNotEmpty() }

private fun SError.toTransportAd() = Error(
    code = code.takeIf { it.isNotBlank() },
    group = group.takeIf { it.isNotBlank() },
    field = field.takeIf { it.isNotBlank() },
    message = message.takeIf { it.isNotBlank() },
)