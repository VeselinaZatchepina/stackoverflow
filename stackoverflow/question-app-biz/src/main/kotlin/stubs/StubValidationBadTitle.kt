package stubs

import com.crowdproj.kotlin.cor.ICorChainDsl
import com.crowdproj.kotlin.cor.handlers.worker
import common.context.SContext
import common.models.SError
import common.models.SState
import common.stub.SStubs

fun ICorChainDsl<SContext>.stubValidationBadTitle(title: String) = worker {
    this.title = title
    on { stubCase == SStubs.BAD_TITLE && state == SState.RUNNING }
    handle {
        state = SState.FAILING
        this.errors.add(
            SError(
                group = "validation",
                code = "validation-title",
                field = "title",
                message = "Wrong title field"
            )
        )
    }
}
