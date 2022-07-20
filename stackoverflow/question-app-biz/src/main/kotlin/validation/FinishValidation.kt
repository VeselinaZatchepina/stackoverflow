package validation

import com.crowdproj.kotlin.cor.ICorChainDsl
import com.crowdproj.kotlin.cor.handlers.worker
import common.context.SContext
import common.models.SState

fun ICorChainDsl<SContext>.finishQuestionValidation(title: String) = worker {
    this.title = title
    on { state == SState.RUNNING }
    handle {
        questionValidated = questionValidating
    }
}
