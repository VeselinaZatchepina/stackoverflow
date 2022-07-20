import common.EMPTY_STRING
import common.context.SContext
import common.models.*
import common.stub.SStubs
import org.stackoverflow.openapi.models.*
import java.time.Instant

fun SContext.fromTransport(request: IRequest) = when(request){
    is CreateQuestionRequest -> fromTransport(request)
    is GetQuestionRequest   -> fromTransport(request)
    is UpdateQuestionRequest -> fromTransport(request)
    is DeleteQuestionRequest -> fromTransport(request)
    is SearchQuestionRequest -> fromTransport(request)
    else -> throw UnknownRequestClass(request.javaClass)
}

private fun String?.toQuestionId() = this?.let { IdModel(it) } ?: IdModel.NONE
private fun IRequest?.requestId() = this?.requestId?.let { RequestId(it) } ?: RequestId.NONE

private fun QuestionDebug?.transportToWorkMode(): WorkMode = when(this?.mode) {
    QuestionRequestDebugMode.PROD -> WorkMode.PROD
    QuestionRequestDebugMode.TEST -> WorkMode.TEST
    QuestionRequestDebugMode.STUB -> WorkMode.STUB
    null -> WorkMode.PROD
}

private fun QuestionDebug?.transportToStubCase(): SStubs = when(this?.stub) {
    QuestionRequestDebugStubs.SUCCESS -> SStubs.SUCCESS
    QuestionRequestDebugStubs.NOT_FOUND -> SStubs.NOT_FOUND
    QuestionRequestDebugStubs.BAD_ID -> SStubs.BAD_ID
    QuestionRequestDebugStubs.BAD_TITLE -> SStubs.BAD_TITLE
    QuestionRequestDebugStubs.BAD_DESCRIPTION -> SStubs.BAD_DESCRIPTION
    QuestionRequestDebugStubs.CANNOT_DELETE -> SStubs.CANNOT_DELETE
    QuestionRequestDebugStubs.BAD_SEARCH_STRING -> SStubs.BAD_SEARCH_STRING
    null -> SStubs.NONE
}

fun SContext.fromTransport(request: CreateQuestionRequest) = apply {
    command = SCommand.CREATE
    requestId = request.requestId()
    questionRequest = request.createQuestion?.toModel() ?: QuestionModel()
    workMode = request.debug.transportToWorkMode()
    stubCase = request.debug.transportToStubCase()
}

fun SContext.fromTransport(request: DeleteQuestionRequest) = apply {
    command = SCommand.DELETE
    requestId = request.requestId()
    questionRequestId = request.questionId.toQuestionId()
    workMode = request.debug.transportToWorkMode()
    stubCase = request.debug.transportToStubCase()
}

fun SContext.fromTransport(request: GetQuestionRequest) = apply {
    command = SCommand.READ
    requestId = request.requestId()
    questionRequestId = request.questionId.toQuestionId()
    workMode = request.debug.transportToWorkMode()
    stubCase = request.debug.transportToStubCase()
}

fun SContext.fromTransport(request: UpdateQuestionRequest) = apply {
    command = SCommand.UPDATE
    requestId = request.requestId()
    questionRequest = request.updateQuestion?.toModel() ?: QuestionModel()
    workMode = request.debug.transportToWorkMode()
    stubCase = request.debug.transportToStubCase()
}

fun SContext.fromTransport(request: SearchQuestionRequest) = apply {
    command = SCommand.SEARCH
    requestId = request.requestId()
    searchQuestionRequest = request.inputText ?: EMPTY_STRING
    workMode = request.debug.transportToWorkMode()
    stubCase = request.debug.transportToStubCase()
}

private fun CreatableQuestion.toModel() = QuestionModel(
    title = title ?: EMPTY_STRING,
    text = text ?: EMPTY_STRING,
)

private fun UpdatableQuestion.toModel() = QuestionModel(
    id = id.toQuestionId(),
    title = title ?: EMPTY_STRING,
    text = text ?: EMPTY_STRING,
    rating = rating ?: EMPTY_STRING,
    ownerId = ownerId ?: EMPTY_STRING,
    status = status?.toModel() ?: QuestionStatusModel.OPENED
)

private fun QuestionStatus.toModel() = enumValueOf<QuestionStatusModel>(name)