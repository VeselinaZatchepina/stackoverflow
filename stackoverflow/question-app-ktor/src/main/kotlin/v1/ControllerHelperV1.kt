package v1

import common.context.SContext
import common.helpers.asSError
import common.models.SCommand
import common.models.SState
import fromTransport
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import kotlinx.datetime.Clock
import org.stackoverflow.openapi.models.IRequest
import org.stackoverflow.openapi.models.IResponse
import toTransportQuestion

suspend inline fun <reified Q : IRequest, reified R : IResponse>
        ApplicationCall.controllerHelperV1(command: SCommand? = null, block: SContext.() -> Unit) {
    val ctx = SContext(
        timeStart = Clock.System.now(),
    )
    try {
        val request = receive<Q>()
        ctx.fromTransport(request)
        ctx.block()
        val response = ctx.toTransportQuestion()
        respond(response)
    } catch (e: Throwable) {
        command?.also { ctx.command = it }
        ctx.state = SState.FAILING
        ctx.errors.add(e.asSError())
        ctx.block()
        val response = ctx.toTransportQuestion()
        respond(response)
    }
}
