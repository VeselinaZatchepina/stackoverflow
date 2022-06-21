package v1

import QuestionService
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Route.v1Question(service: QuestionService) {
    route("question") {
        post("create") {
            call.createQuestion(service)
        }
        post("read") {
            call.readQuestion(service)
        }
        post("update") {
            call.updateQuestion(service)
        }
        post("delete") {
            call.deleteQuestion(service)
        }
        post("search") {
            call.searchQuestion(service)
        }
    }
}