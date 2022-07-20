package stubs

import Question
import com.crowdproj.kotlin.cor.ICorChainDsl
import com.crowdproj.kotlin.cor.handlers.worker
import common.context.SContext
import common.models.IdModel
import common.models.SState
import common.stub.SStubs

fun ICorChainDsl<SContext>.stubCreateSuccess(title: String) = worker {
    this.title = title
    on { stubCase == SStubs.SUCCESS && state == SState.RUNNING }
    handle {
        state = SState.FINISHING
        val stub = Question.getModel().apply {
            questionRequest.id.takeIf { it != IdModel.NONE }?.also { this.id = it }
            questionRequest.title.takeIf { it.isNotBlank() }?.also { this.title = it }
            questionRequest.text.takeIf { it.isNotBlank() }?.also { this.text = it }
            questionRequest.status.also { this.status = it }
        }
        questionResponse = stub
    }
}
