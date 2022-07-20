package general

import com.crowdproj.kotlin.cor.ICorChainDsl
import com.crowdproj.kotlin.cor.handlers.worker
import common.context.SContext
import common.models.SState

fun ICorChainDsl<SContext>.initStatus(title: String) = worker() {
    this.title = title
    on { state == SState.NONE }
    handle { state = SState.RUNNING }
}