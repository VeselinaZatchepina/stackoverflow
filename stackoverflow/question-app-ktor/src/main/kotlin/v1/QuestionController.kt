package v1

import QuestionService
import common.context.SContext
import common.helpers.asSError
import common.models.SCommand
import common.models.SState
import fromTransport
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import kotlinx.datetime.Clock
import org.stackoverflow.openapi.models.*
import toTransportCreate
import toTransportDelete
import toTransportQuestion
import toTransportRead
import toTransportSearch
import toTransportUpdate

suspend fun ApplicationCall.createQuestion(service: QuestionService) {
    val ctx = SContext(
        timeStart = Clock.System.now(),
    )
    try {
        val request = receive<CreateQuestionRequest>()
        ctx.fromTransport(request)
        service.createAd(ctx)
        val response = ctx.toTransportQuestion()
        respond(response)
    } catch (e: Throwable) {
        ctx.command = SCommand.CREATE
        ctx.state = SState.FAILING
        ctx.errors.add(e.asSError())
        service.createAd(ctx)
        val response = ctx.toTransportQuestion()
        respond(response)
    }
}

suspend fun ApplicationCall.readQuestion(service: QuestionService) =
    controllerHelperV1<GetQuestionRequest, GetQuestionResponse>(SCommand.READ) {
        service.readAd(this)
    }

suspend fun ApplicationCall.updateQuestion(service: QuestionService) =
    controllerHelperV1<UpdateQuestionRequest, UpdateQuestionResponse>(SCommand.UPDATE) {
        service.updateAd(this)
    }

suspend fun ApplicationCall.deleteQuestion(service: QuestionService) =
    controllerHelperV1<DeleteQuestionRequest, DeleteQuestionResponse>(SCommand.DELETE) {
        service.deleteAd(this)
    }

suspend fun ApplicationCall.searchQuestion(service: QuestionService) =
    controllerHelperV1<SearchQuestionRequest, SearchQuestionResponse>(SCommand.SEARCH) {
        service.searchAd(this)
    }
