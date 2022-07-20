package general

import com.crowdproj.kotlin.cor.ICorChainDsl
import com.crowdproj.kotlin.cor.handlers.chain
import common.context.SContext
import common.models.SCommand
import common.models.SState

fun ICorChainDsl<SContext>.operation(title: String, command: SCommand, block: ICorChainDsl<SContext>.() -> Unit) = chain {
    block()
    this.title = title
    on { this.command == command && state == SState.RUNNING }
}