package common.context

import common.models.IError
import common.models.IdModel
import common.models.StubCase
import common.models.WorkMode
import java.time.Instant

interface IContext {
    val startTime: Instant
    val operation: Operations
    val workMode: WorkMode
    val stubCase: StubCase

    val onRequest: String
    val requestId: IdModel

    val errors: MutableList<IError>
    var status: CoreStatus

    enum class Operations {
        NONE,
        CREATE,
        GET,
        SEARCH
    }
}