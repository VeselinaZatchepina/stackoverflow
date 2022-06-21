package stubs

import Question
import com.crowdproj.kotlin.cor.ICorChainDsl
import com.crowdproj.kotlin.cor.handlers.worker
import common.context.SContext
import common.models.SState
import common.stub.SStubs

fun ICorChainDsl<SContext>.stubSearchSuccess(title: String) = worker {
    this.title = title
    on { stubCase == SStubs.SUCCESS && state == SState.RUNNING }
    handle {
        state = SState.FINISHING
        questionsResponse.addAll(Question.getModels())
    }
}
