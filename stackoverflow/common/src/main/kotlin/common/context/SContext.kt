package common.context

import common.EMPTY_STRING
import common.NONE
import common.models.*
import common.stub.SStubs
import kotlinx.datetime.Instant

data class SContext(
    var command: SCommand = SCommand.NONE,
    var state: SState = SState.NONE,
    val errors: MutableList<SError> = mutableListOf(),

    var workMode: WorkMode = WorkMode.PROD,
    var stubCase: SStubs = SStubs.NONE,

    var requestId: RequestId = RequestId.NONE,
    var timeStart: Instant = Instant.NONE,

    var questionRequest: QuestionModel = QuestionModel(),
    var questionRequestId: IdModel = IdModel.NONE,
    var searchQuestionRequest: String = EMPTY_STRING,

    val questionResponse: QuestionModel = QuestionModel(),
    var questionsResponse: MutableList<QuestionModel> = mutableListOf(),
)