package common.context

import common.models.*
import java.time.Instant

data class SContext(
    override val startTime : Instant = Instant.MIN,
    override var operation: IContext.Operations = IContext.Operations.NONE,
    override var workMode: WorkMode = WorkMode.PROD,
    override var stubCase: StubCase = StubCase.NONE,

    override var onRequest: String = EMPTY_STRING,
    override val requestId: IdModel = IdModel.NONE,

    var userRequest: UserModel = UserModel(),
    var userRequestId: IdModel = IdModel.NONE,
    var questionRequestId: IdModel = IdModel.NONE,
    var questionRequest: QuestionModel = QuestionModel(),
    var answerRequest: AnswerModel = AnswerModel(),
    var searchQuestionRequest: String = EMPTY_STRING,

    val userResponse: UserModel = UserModel(),
    val questionResponse: QuestionModel = QuestionModel(),
    val answerResponse: AnswerModel = AnswerModel(),
    var answersResponse: MutableList<AnswerModel> = mutableListOf(),
    var questionsResponse: MutableList<QuestionModel> = mutableListOf(),

    override val errors: MutableList<IError> = mutableListOf(),
    override var status: CoreStatus = CoreStatus.NONE,
) : IContext {


    /**
     * Добавляет ошибку в контекст
     *
     * @param error Ошибка, которую необходимо добавить в контекст
     * @param failingStatus Необходимо ли установить статус выполнения в FAILING (true/false)
     */
    suspend fun addError(error: IError, failingStatus: Boolean = true) = apply {
        if (failingStatus) status = CoreStatus.FAILING
        errors.add(error)
    }


    suspend fun addError(
        e: Throwable,
        level: IError.Level = IError.Level.ERROR,
        field: String = "",
        failingStatus: Boolean = true
    ) {
        addError(CommonErrorModel(e, field = field, level = level), failingStatus)
    }
}
