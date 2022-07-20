package stubs

import com.crowdproj.kotlin.cor.ICorChainDsl
import com.crowdproj.kotlin.cor.handlers.chain
import common.context.SContext
import common.models.SState
import common.models.WorkMode

fun ICorChainDsl<SContext>.stubs(title: String, block: ICorChainDsl<SContext>.() -> Unit) = chain {
    block()
    this.title = title
    on { workMode == WorkMode.STUB && state == SState.RUNNING }
}
