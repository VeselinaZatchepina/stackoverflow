import common.context.SContext
import common.models.*
import org.stackoverflow.openapi.models.*

fun SContext.toCreateUserResponse() = CreateUserResponse(
    requestId = onRequest.takeIf { it.isNotBlank() },
    errors = errors.takeIf { it.isNotEmpty() }?.map { it.toTransport() },
    result = if (errors.find { it.level == IError.Level.ERROR } == null) CreateUserResponse.Result.SUCCESS
    else CreateUserResponse.Result.ERROR,
    createdUser = userResponse.takeIf { it != UserModel() }?.toTransport(),
)

fun SContext.toCreateQuestionResponse() = CreateQuestionResponse(
    requestId = onRequest.takeIf { it.isNotBlank() },
    errors = errors.takeIf { it.isNotEmpty() }?.map { it.toTransport() },
    result = if (errors.find { it.level == IError.Level.ERROR } == null) CreateQuestionResponse.Result.SUCCESS
    else CreateQuestionResponse.Result.ERROR,
    createdQuestion = questionResponse.takeIf { it != QuestionModel() }?.toTransport(),
)

fun SContext.toCreateAnswerResponse() = CreateAnswerResponse(
    requestId = onRequest.takeIf { it.isNotBlank() },
    errors = errors.takeIf { it.isNotEmpty() }?.map { it.toTransport() },
    result = if (errors.find { it.level == IError.Level.ERROR } == null) CreateAnswerResponse.Result.SUCCESS
    else CreateAnswerResponse.Result.ERROR,
    createdAnswer = answerResponse.takeIf { it != AnswerModel() }?.toTransport(),
)

fun SContext.toGetAnswerResponse() = GetAnswersResponse(
    requestId = onRequest.takeIf { it.isNotBlank() },
    errors = errors.takeIf { it.isNotEmpty() }?.map { it.toTransport() },
    result = if (errors.find { it.level == IError.Level.ERROR } == null) GetAnswersResponse.Result.SUCCESS
    else GetAnswersResponse.Result.ERROR,
    answers = answersResponse.takeIf { it.isNotEmpty() }?.map { it.toTransport() },
)

fun SContext.toGetQuestionResponse() = GetQuestionResponse(
    requestId = onRequest.takeIf { it.isNotBlank() },
    errors = errors.takeIf { it.isNotEmpty() }?.map { it.toTransport() },
    result = if (errors.find { it.level == IError.Level.ERROR } == null) GetQuestionResponse.Result.SUCCESS
    else GetQuestionResponse.Result.ERROR,
    question = questionResponse.takeIf { it != QuestionModel() }?.toTransport(),
)

fun SContext.toGetUserResponse() = GetUserResponse(
    requestId = onRequest.takeIf { it.isNotBlank() },
    errors = errors.takeIf { it.isNotEmpty() }?.map { it.toTransport() },
    result = if (errors.find { it.level == IError.Level.ERROR } == null) GetUserResponse.Result.SUCCESS
    else GetUserResponse.Result.ERROR,
    user = userResponse.takeIf { it != UserModel() }?.toTransport(),
)

fun SContext.toSearchQuestionResponse() = SearchQuestionResponse(
    requestId = onRequest.takeIf { it.isNotBlank() },
    errors = errors.takeIf { it.isNotEmpty() }?.map { it.toTransport() },
    result = if (errors.find { it.level == IError.Level.ERROR } == null) SearchQuestionResponse.Result.SUCCESS
    else SearchQuestionResponse.Result.ERROR,
    questions = questionsResponse.takeIf { it.isNotEmpty() }?.map { it.toTransport() },
)

private fun IError.toTransport() = RequestError(
    message = message.takeIf { it.isNotBlank() },
    field = field.takeIf { it.isNotBlank() },
)

private fun UserModel.toTransport() = User(
    id = id.takeIf { it != IdModel.NONE }?.asString(),
    name = name,
    email = email,
    rating = rating
)

private fun QuestionModel.toTransport() = Question(
    id = id.takeIf { it != IdModel.NONE }?.asString(),
    text = text,
    rating = rating,
    creationDate = creationDate.toString(),
    status = status.toTransport()
)

private fun AnswerModel.toTransport() = Answer(
    id = id.takeIf { it != IdModel.NONE }?.asString(),
    text = text,
    rating = rating,
    creationDate = creationDate.toString(),
)

private fun QuestionStatusModel.toTransport() = enumValueOf<QuestionStatus>(name)