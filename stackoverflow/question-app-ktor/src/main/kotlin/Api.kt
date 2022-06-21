import io.ktor.server.routing.*
import v1.v1Question

internal fun Routing.v1(service: QuestionService) {
    route("v1") {
        v1Question(service)
    }
}