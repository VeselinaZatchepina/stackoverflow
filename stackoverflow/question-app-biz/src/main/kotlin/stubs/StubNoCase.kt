package stubs

import com.crowdproj.kotlin.cor.ICorChainDsl
import com.crowdproj.kotlin.cor.handlers.worker
import common.context.SContext
import common.models.SError
import common.models.SState

fun ICorChainDsl<SContext>.stubNoCase(title: String) = worker {
    this.title = title
    on { state == SState.RUNNING }
    handle {
        state = SState.FAILING
        this.errors.add(
            SError(
                code = "validation",
                field = "stub",
                group = "validation",
                message = "Wrong stub case is requested: ${stubCase.name}"
            )
        )
    }
}
