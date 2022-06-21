import common.context.SContext
import common.models.SError
import common.models.WorkMode
import common.stub.SStubs

class QuestionService {

    fun createQuestion(context: SContext): SContext {
        val response = when (context.workMode) {
            WorkMode.PROD -> TODO()
            WorkMode.TEST -> context.questionRequest
            WorkMode.STUB -> Question.getModel()
        }
        return context.successResponse {
            questionResponse = response
        }
    }

    fun readQuestion(context: SContext, buildError: () -> SError): SContext {
        val requestedId = context.questionRequestId

        return when (context.stubCase) {
            SStubs.SUCCESS -> context.successResponse {
                questionResponse = Question.getModel().apply { id = requestedId }
            }
            else -> context.errorResponse(buildError) {
                it.copy(field = "question.id", message = notFoundError(requestedId.asString()))
            }
        }
    }

    fun updateQuestion(context: SContext, buildError: () -> SError) = when (context.stubCase) {
        SStubs.SUCCESS -> context.successResponse {
            questionResponse = Question.getModel()
        }
        else -> context.errorResponse(buildError) {
            it.copy(field = "question.id", message = notFoundError(context.questionRequest.id.asString()))
        }
    }


    fun deleteQuestion(context: SContext, buildError: () -> SError): SContext = when (context.stubCase) {
        SStubs.SUCCESS -> context.successResponse {
            questionResponse = Question.getModel().apply { id = context.questionRequestId }
        }
        else -> context.errorResponse(buildError) {
            it.copy(
                field = "question.id",
                message = notFoundError(context.questionRequest.id.asString())
            )
        }
    }

    fun searchQuestion(context: SContext, buildError: () -> SError): SContext {
        val searchableString = context.searchQuestionRequest

        return when (context.stubCase) {
            SStubs.SUCCESS -> context.successResponse {
                questionsResponse.addAll(
                    Question.getModels()
                )
            }
            else -> context.errorResponse(buildError) {
                it.copy(
                    message = "Nothing found by $searchableString"
                )
            }
        }
    }
}