import common.context.IContext
import common.context.SContext
import common.models.*
import org.stackoverflow.openapi.models.*
import java.time.Instant

fun SContext.setQuery(query: CreateAnswerRequest) = apply {
    operation = IContext.Operations.CREATE
    onRequest = query.requestId ?: EMPTY_STRING
    answerRequest = query.createAnswer?.toModel()?: AnswerModel()
    workMode = query.debug?.mode.toModel()
    stubCase = query.debug?.stubCase?.takeIf { workMode == WorkMode.STUB }.toModel()
}

fun SContext.setQuery(query: CreateQuestionRequest) = apply {
    operation = IContext.Operations.CREATE
    onRequest = query.requestId ?: EMPTY_STRING
    questionRequest = query.createQuestion?.toModel() ?: QuestionModel()
    workMode = query.debug?.mode.toModel()
    stubCase = query.debug?.stubCase?.takeIf { workMode == WorkMode.STUB }.toModel()
}

fun SContext.setQuery(query: CreateUserRequest) = apply {
    operation = IContext.Operations.CREATE
    onRequest = query.requestId ?: EMPTY_STRING
    userRequest = query.createUser?.toModel() ?: UserModel()
    workMode = query.debug?.mode.toModel()
    stubCase = query.debug?.stubCase?.takeIf { workMode == WorkMode.STUB }.toModel()
}

fun SContext.setQuery(query: GetAnswersRequest) = apply {
    operation = IContext.Operations.CREATE
    onRequest = query.requestId ?: EMPTY_STRING
    questionRequestId = IdModel(query.questionId ?: EMPTY_STRING)
    workMode = query.debug?.mode.toModel()
    stubCase = query.debug?.stubCase?.takeIf { workMode == WorkMode.STUB }.toModel()
}

fun SContext.setQuery(query: GetQuestionRequest) = apply {
    operation = IContext.Operations.CREATE
    onRequest = query.requestId ?: EMPTY_STRING
    questionRequestId = IdModel(query.questionId ?: EMPTY_STRING)
    workMode = query.debug?.mode.toModel()
    stubCase = query.debug?.stubCase?.takeIf { workMode == WorkMode.STUB }.toModel()
}

fun SContext.setQuery(query: GetUserRequest) = apply {
    operation = IContext.Operations.CREATE
    onRequest = query.requestId ?: EMPTY_STRING
    userRequestId = IdModel(query.userId ?: EMPTY_STRING)
    workMode = query.debug?.mode.toModel()
    stubCase = query.debug?.stubCase?.takeIf { workMode == WorkMode.STUB }.toModel()
}

fun SContext.setQuery(query: SearchQuestionRequest) = apply {
    operation = IContext.Operations.CREATE
    onRequest = query.requestId ?: EMPTY_STRING
    searchQuestionRequest = query.inputText ?: EMPTY_STRING
    workMode = query.debug?.mode.toModel()
    stubCase = query.debug?.stubCase?.takeIf { workMode == WorkMode.STUB }.toModel()
}

private fun User.toModel() = UserModel(
    id = IdModel(id ?: EMPTY_STRING),
    name = name ?: EMPTY_STRING,
    rating = rating ?: EMPTY_STRING,
    email = email ?: EMPTY_STRING,
)

private fun Question.toModel() = QuestionModel(
    id = IdModel(id ?: EMPTY_STRING),
    text = text ?: EMPTY_STRING,
    rating = rating ?: EMPTY_STRING,
    creationDate = Instant.now(),
    status = status?.toModel() ?: QuestionStatusModel.NONE
)

private fun Answer.toModel() = AnswerModel(
    id = IdModel(id ?: EMPTY_STRING),
    text = text ?: EMPTY_STRING,
    rating = rating ?: EMPTY_STRING,
    creationDate = Instant.now()
)

private fun BaseDebugRequest.Mode?.toModel() = when(this) {
    BaseDebugRequest.Mode.STUB -> WorkMode.STUB
    BaseDebugRequest.Mode.TEST -> WorkMode.TEST
    BaseDebugRequest.Mode.PROD -> WorkMode.PROD
    null -> WorkMode.PROD
}

private fun BaseDebugRequest.StubCase?.toModel() = when(this) {
    BaseDebugRequest.StubCase.SUCCESS -> StubCase.SUCCESS
    BaseDebugRequest.StubCase.DATABASE_ERROR -> StubCase.DATABASE_ERROR
    null -> StubCase.NONE
}

private fun QuestionStatus.toModel() = enumValueOf<QuestionStatusModel>(name)