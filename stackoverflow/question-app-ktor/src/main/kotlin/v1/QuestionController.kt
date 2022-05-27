package v1

import QuestionService
import common.context.SContext
import fromTransport
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import org.stackoverflow.openapi.models.*
import toTransportCreate
import toTransportDelete
import toTransportRead
import toTransportSearch
import toTransportUpdate

suspend fun ApplicationCall.createQuestion(service: QuestionService) {
    val createQuestionRequest = receive<CreateQuestionRequest>()
    respond(
        SContext().apply { fromTransport(createQuestionRequest) }.let {
            service.createQuestion(it)
        }.toTransportCreate()
    )
}

suspend fun ApplicationCall.readQuestion(service: QuestionService) {
    val readAdRequest = receive<GetQuestionRequest>()
    respond(
        SContext().apply { fromTransport(readAdRequest) }.let {
            service.readQuestion(it, ::buildError)
        }.toTransportRead()
    )
}

suspend fun ApplicationCall.updateQuestion(service: QuestionService) {
    val updateAdRequest = receive<UpdateQuestionRequest>()
    respond(
        SContext().apply { fromTransport(updateAdRequest) }.let {
            service.updateQuestion(it, ::buildError)
        }.toTransportUpdate()
    )
}

suspend fun ApplicationCall.deleteQuestion(service: QuestionService) {
    val deleteAdRequest = receive<DeleteQuestionRequest>()
    respond(
        SContext().apply { fromTransport(deleteAdRequest) }.let {
            service.deleteQuestion(it, ::buildError)
        }.toTransportDelete()
    )
}

suspend fun ApplicationCall.searchQuestion(service: QuestionService) {
    val searchAdRequest = receive<SearchQuestionRequest>()
    respond(
        SContext().apply { fromTransport(searchAdRequest) }.let {
            service.searchQuestion(it, ::buildError)
        }.toTransportSearch()
    )
}